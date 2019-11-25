package vn.nuce.web.logic.controller.admin;

import vn.nuce.core.dto.BillDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.web.logic.command.BillCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.List;

@WebServlet("/admin-charts.html")
public class ReportController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BillCommand billCommand = FormUtil.populate(BillCommand.class, request);
        List<BillDTO> list = SingletonServiceUtil.getBillServiceInstance().findAllBill();
        List<Integer> earningsList = new ArrayList<Integer>();
        earningsList = buildEarningsMonthList(earningsList, list);
        List<BillDTO> result = buildListResult(list);
        Integer earningsMonthly = compute(result);
        billCommand.setEarningsMonth(earningsMonthly);
        billCommand.setListResult(buildDistinctListResult(result));
        request.setAttribute(WebConstant.EARNINGS_LIST,earningsList);
        request.setAttribute(WebConstant.LIST_ITEMS, billCommand.getListResult());
        request.setAttribute(WebConstant.EARNINGS_MONTHLY, billCommand.getEarningsMonth());
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/report.jsp");
        rd.forward(request, response);
    }

    private List<Integer> buildEarningsMonthList(List<Integer> earningsList, List<BillDTO> list) {
        for (int i = 0; i < 12; i++) {
            earningsList.add(0);
        }
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        for (BillDTO item : list) {
            calendar.setTime(item.getCreatedDate());
            if (calendar.get(Calendar.YEAR) == year) {
                for (int i = 0; i < 12; i++) {
                    if (calendar.get(Calendar.MONTH) == i) {
                        earningsList.set(i,earningsList.get(i) + item.getTotalMoney());
                    }
                }
            }
        }
        return earningsList;
    }

    private Integer compute(List<BillDTO> result) {
        Integer total = 0;
        for (BillDTO item : result) {
            total += item.getTotalMoney();
        }
        return total;
    }

    private List<BillDTO> buildDistinctListResult(List<BillDTO> result) {
        List<BillDTO> rslt = new ArrayList<BillDTO>();
        try {
            for (BillDTO item : result) {
                boolean check = true;
                for (int i = 0; i < rslt.size(); i++) {
                    if (rslt.get(i).getUserDTO().getUserId() == item.getUserDTO().getUserId()) {
                        check = false;
                        rslt.get(i).setTotalMoney(rslt.get(i).getTotalMoney() + item.getTotalMoney());
                    }
                }
                if (check) {
                    rslt.add(item);
                }
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
        return rslt;
    }

    private List<BillDTO> buildListResult(List<BillDTO> list) {
        List<BillDTO> result = new ArrayList<BillDTO>();
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        for (BillDTO item : list) {
            calendar.setTime(item.getCreatedDate());
            if (calendar.get(Calendar.MONTH) == month && calendar.get(Calendar.YEAR) == year) {
                result.add(item);
            }
        }
        return result;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
