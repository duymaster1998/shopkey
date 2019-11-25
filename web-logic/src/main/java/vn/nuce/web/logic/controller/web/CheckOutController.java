package vn.nuce.web.logic.controller.web;

import vn.nuce.core.common.utils.SessionUtil;
import vn.nuce.core.dto.*;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/check-out.html")
public class CheckOutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BillCommand command = FormUtil.populate(BillCommand.class, request);
        String status = request.getParameter("status");
        Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
        OrderDTO orderDTO = (OrderDTO) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId);
        if (orderDTO != null) {
            if (status.equals("views")) {

            } else if (status.equals("check-out")) {
                BillDTO dto = new BillDTO();
                dto.setUserDTO(orderDTO.getUserDTO());
                dto.setTotalMoney(command.getPojo().getTotalMoney());
                SingletonServiceUtil.getBillServiceInstance().saveBill(dto);
                Integer[] listId = new Integer[orderDTO.getItemDTOS().size()];
                Integer[] listQuantity = new Integer[orderDTO.getItemDTOS().size()];
                int i = 0;
                for (ItemDTO item : orderDTO.getItemDTOS()) {
                    listId[i] = item.getProductDTO().getProductId();
                    listQuantity[i] = item.getQuantity();
                    i++;
                }
                List<ArrayList<ProductDetailDTO>> listOfLists = new ArrayList<ArrayList<ProductDetailDTO>>();
                for (int i1 = 0; i1 < listId.length; i1++) {
                    List<ProductDetailDTO> list = SingletonServiceUtil.getProductDetailServiceInstance().selectTopProductDetail(listId[i1], listQuantity[i1]);
                    listOfLists.add((ArrayList<ProductDetailDTO>) list);
                }
                for (int i1 = 0; i1 < listOfLists.size(); i1++) {
                    for (int i2 = 0; i2 < listOfLists.get(i1).size(); i2++) {
                        SingletonServiceUtil.getProductDetailServiceInstance().updateProductDetail(listOfLists.get(i1).get(i2));
                    }
                }
                Integer billId = SingletonServiceUtil.getBillServiceInstance().findBillByDesc();
                BillDTO billDTO = new BillDTO();
                billDTO.setBillId(billId);
                billDTO.setUserDTO(orderDTO.getUserDTO());
                BillDetailDTO billDetailDTO = new BillDetailDTO();
                for (int i1 = 0; i1 < listOfLists.size(); i1++) {
                    for (int i2 = 0; i2 < listOfLists.get(i1).size(); i2++) {
                        billDetailDTO.setProductDetailDTO(listOfLists.get(i1).get(i2));
                        billDetailDTO.setPrice(orderDTO.getItemDTOS().get(i1).getPrice());
                        billDetailDTO.setBillDTO(billDTO);
                        SingletonServiceUtil.getBillDetailServiceInstance().save(billDetailDTO);
                    }
                }
                List<CheckOutResultDTO> resultDTOS = new ArrayList<CheckOutResultDTO>();
                for (int i1 = 0; i1 < listOfLists.size(); i1++) {
                    for (int i2 = 0; i2 < listOfLists.get(i1).size(); i2++) {
                        CheckOutResultDTO dto1 = new CheckOutResultDTO();
                        dto1.setName(orderDTO.getItemDTOS().get(i1).getProductDTO().getProductName());
                        dto1.setKey(listOfLists.get(i1).get(i2).getProductKey());
                        resultDTOS.add(dto1);
                    }
                }
                UserDTO userDTO = SingletonServiceUtil.getUserServiceInstance().findById(userId);
                userDTO.setBalance(userDTO.getBalance() - command.getPojo().getTotalMoney());
                SingletonServiceUtil.getUserServiceInstance().updateUser(userDTO);
                SessionUtil.getInstance().putValue(request.getSession(), WebConstant.BALANCE, userDTO.getBalance());
                request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
                request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Thanh toán thành công vui lòng kéo xuống để xem key game !");
                request.setAttribute(WebConstant.LIST_ITEMS, resultDTOS);
                SessionUtil.getInstance().removeValue(request.getSession(),WebConstant.ORDER + userId);
            }
            if (SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId) != null) {
                request.setAttribute(WebConstant.ORDER_USER, SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId));
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/shop/checkout.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
