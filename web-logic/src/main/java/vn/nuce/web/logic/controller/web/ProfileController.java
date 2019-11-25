package vn.nuce.web.logic.controller.web;

import org.apache.log4j.Logger;
import vn.nuce.core.common.utils.SessionUtil;
import vn.nuce.core.dto.BillDTO;
import vn.nuce.core.dto.CheckOutResultDTO;
import vn.nuce.core.dto.UserDTO;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.core.web.utils.WebCommonUtil;
import vn.nuce.web.logic.command.BillCommand;
import vn.nuce.web.logic.command.UserCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet(urlPatterns = {"/account-profile.html","/ajax-detail-bill.html"})
public class ProfileController extends HttpServlet {
    private transient final Logger log = Logger.getLogger(this.getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        Map<String, String> mapMess = buildMapMessage(resourceBundle);
        WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMess);
        try {
            if (request.getSession() != null) {
                if (SessionUtil.getInstance().getValue(request.getSession(), WebConstant.LOGIN_NAME) != null) {
                    Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
                    command.setPojo(SingletonServiceUtil.getUserServiceInstance().findById(userId));
                    request.setAttribute(WebConstant.FROM_ITEM, command);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (status.equals(WebConstant.USER_ACCOUNT)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/account/profile.jsp");
            rd.forward(request, response);
        } else if (status.equals(WebConstant.USER_PERSONAL)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/account/personal.jsp");
            rd.forward(request, response);
        } else if (status.equals(WebConstant.USER_CHANGE_PASSWORD)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/account/change-password.jsp");
            rd.forward(request, response);
        } else if (status.equals(WebConstant.USER_TRANSACTION)) {
            Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
            BillCommand billCommand = FormUtil.populate(BillCommand.class,request);
            if (billCommand.getUrlType().equals(WebConstant.URL_LIST)) {
                List<BillDTO> billDTOS = SingletonServiceUtil.getBillServiceInstance().findAllBill();
                List<BillDTO> temp = new ArrayList<BillDTO>();
                for (BillDTO item : billDTOS) {
                    if (item.getUserDTO().getUserId().equals(userId)) {
                        temp.add(item);
                    }
                }
                billCommand.setListResult(temp);
                request.setAttribute(WebConstant.LIST_ITEMS,billCommand.getListResult());
                RequestDispatcher rd = request.getRequestDispatcher("/views/web/account/transaction.jsp");
                rd.forward(request, response);
            } else if (billCommand.getUrlType().equals(WebConstant.URL_EDIT)) {
                List<CheckOutResultDTO> dtos = SingletonServiceUtil.getBillDetailServiceInstance().findById(billCommand.getPojo().getBillId());
                request.setAttribute(WebConstant.LIST_ITEMS,dtos);
                RequestDispatcher rd = request.getRequestDispatcher("/views/web/account/detail-bill.jsp");
                rd.forward(request, response);
            }
        } else if (status.equals(WebConstant.USER_PAYIN)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/account/payin.jsp");
            rd.forward(request, response);
        }
    }

    private Map<String, String> buildMapMessage(ResourceBundle bundle) {
        Map<String, String> mapMess = new HashMap<String, String>();
        mapMess.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.guideline.listen.add.success"));
        mapMess.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.guideline.listen.update.success"));
        mapMess.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.guideline.listen.delete.success"));
        mapMess.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.error"));
        return mapMess;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        String string = request.getParameter("status");
        if (string.equals(WebConstant.USER_CHANGE_PASSWORD)) {
            if (request.getSession() != null) {
                if (SessionUtil.getInstance().getValue(request.getSession(), WebConstant.LOGIN_NAME) != null) {
                    Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
                    command.setPojo(SingletonServiceUtil.getUserServiceInstance().findById(userId));
                    if (command.getCurrentPassword().equals(command.getPojo().getPassword())) {
                        command.getPojo().setPassword(command.getNewPassword());
                        SingletonServiceUtil.getUserServiceInstance().updateUser(command.getPojo());
                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Cập nhật mật khẩu thành công");
                    } else {
                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Mật khẩu hiện tại không chính xác");
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/account/change-password.jsp");
            rd.forward(request, response);
        } else if (string.equals(WebConstant.USER_PERSONAL)) {
            Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
            command.setPojo(SingletonServiceUtil.getUserServiceInstance().findById(userId));
            UserDTO pojo = command.getPojo();
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String homeTown = request.getParameter("homeTown");
            byte[] bytes = firstName.getBytes(StandardCharsets.ISO_8859_1);
            firstName = new String(bytes, StandardCharsets.UTF_8);
            bytes = lastName.getBytes(StandardCharsets.ISO_8859_1);
            lastName = new String(bytes, StandardCharsets.UTF_8);
            bytes = homeTown.getBytes(StandardCharsets.ISO_8859_1);
            homeTown = new String(bytes,StandardCharsets.UTF_8);
            pojo.setFirstName(firstName);
            pojo.setLastName(lastName);
            pojo.setHomeTown(homeTown);
            pojo.setPhoneNumber(command.getNewPhoneNumber());
            SingletonServiceUtil.getUserServiceInstance().updateUser(pojo);
            response.sendRedirect("/account-profile.html?status=personal&&crudaction=redirect_update");
        } else if (string.equals(WebConstant.USER_PAYIN)) {
            Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
            command.setPojo(SingletonServiceUtil.getUserServiceInstance().findById(userId));
            UserDTO pojo = command.getPojo();
            pojo.setBalance(pojo.getBalance() + command.getAmount());
            SingletonServiceUtil.getUserServiceInstance().updateUser(pojo);
            SessionUtil.getInstance().putValue(request.getSession(),WebConstant.BALANCE,pojo.getBalance());
            response.sendRedirect("/account-profile.html?status=pay-in&&crudaction=redirect_update");
        }
    }
}
