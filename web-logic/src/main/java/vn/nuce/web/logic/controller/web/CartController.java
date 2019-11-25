package vn.nuce.web.logic.controller.web;

import com.lowagie.text.pdf.AcroFields;
import org.apache.log4j.Logger;
import vn.nuce.core.common.utils.SessionUtil;
import vn.nuce.core.dto.ItemDTO;
import vn.nuce.core.dto.OrderDTO;
import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.dto.UserDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.core.web.utils.WebCommonUtil;
import vn.nuce.web.logic.command.ProductCommand;
import vn.nuce.web.logic.command.UserCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart.html")
public class CartController extends HttpServlet {
    private transient final Logger log = Logger.getLogger(this.getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductCommand command = FormUtil.populate(ProductCommand.class, request);
        UserCommand userCommand = FormUtil.populate(UserCommand.class, request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        Map<String, String> mapMess = buildMapMessage(resourceBundle);
        WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMess);
        Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
//        SessionUtil.getInstance().removeValue(request.getSession(),WebConstant.ORDER + userId);
        if (command.getPojo().getProductId() != null) {
            ProductDTO pojo = SingletonServiceUtil.getProductServiceInstance().findById(command.getPojo().getProductId());
            int quantity;
            if (command.getProductDetailQty() != null) {
                quantity = command.getProductDetailQty();
            } else {
                quantity = 1;
            }
            if (pojo != null) {
                if (command.getCrudaction().equals(WebConstant.PRODUCT_INSERT)) {
                    if (SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId) == null) {
                        OrderDTO orderDTO = new OrderDTO();
                        List<ItemDTO> itemDTOS = new ArrayList<ItemDTO>();
                        ItemDTO itemDTO = new ItemDTO();
                        itemDTO.setQuantity(quantity);
                        itemDTO.setPrice(pojo.getPrice());
                        itemDTO.setProductDTO(pojo);
                        itemDTO.getProductDTO().setQuantity(SingletonServiceUtil.getProductDetailServiceInstance().selectTopProductDetail(pojo.getProductId(),null).size());
                        itemDTOS.add(itemDTO);
                        orderDTO.setItemDTOS(itemDTOS);
                        userCommand.setPojo(SingletonServiceUtil.getUserServiceInstance().findById(userId));
                        UserDTO userDTO = userCommand.getPojo();
                        orderDTO.setUserDTO(userDTO);
                        SessionUtil.getInstance().putValue(request.getSession(), WebConstant.ORDER + userId, orderDTO);
                    } else {
                        OrderDTO orderDTO = (OrderDTO) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId);
                        List<ItemDTO> itemDTOS = orderDTO.getItemDTOS();
                        boolean check = false;
                        for (ItemDTO item : itemDTOS) {
                            item.getProductDTO().setQuantity(SingletonServiceUtil.getProductDetailServiceInstance().selectTopProductDetail(item.getProductDTO().getProductId(), null).size());
                        }
                        for (ItemDTO item : itemDTOS) {
                            if (item.getProductDTO().getProductId().equals(pojo.getProductId())) {
                                if (item.getQuantity() + quantity > item.getProductDTO().getQuantity()) {

                                } else {
                                    item.setQuantity(item.getQuantity() + quantity);
                                }
                                check = true;
                            }
                        }
                        if (check == false) {
                            ItemDTO itemDTO = new ItemDTO();
                            itemDTO.setQuantity(quantity);
                            itemDTO.setPrice(pojo.getPrice());
                            itemDTO.setProductDTO(pojo);
                            itemDTO.getProductDTO().setQuantity(SingletonServiceUtil.getProductDetailServiceInstance().selectTopProductDetail(itemDTO.getProductDTO().getProductId(), null).size());
                            itemDTOS.add(itemDTO);
                        }
                        SessionUtil.getInstance().putValue(request.getSession(), WebConstant.ORDER + userId, orderDTO);
                    }
                    request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
                    request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Thêm sản phẩm vào giỏ hàng thành công !");
                } else if (command.getCrudaction().equals(WebConstant.PRODUCT_DELETE)) {
                    OrderDTO orderDTO = (OrderDTO) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId);
                    try {
                        for (ItemDTO item : orderDTO.getItemDTOS()) {
                            if (item.getProductDTO().getProductId().equals(pojo.getProductId())) {
                                orderDTO.getItemDTOS().remove(item);
                            }
                        }
                        SessionUtil.getInstance().putValue(request.getSession(), WebConstant.ORDER + userId, orderDTO);
                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Xóa sản phẩm thành công !");
                    } catch (ConcurrentModificationException e) {
                        log.error(e.getMessage(), e);
                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Xóa sản phẩm thành công !");
                    }
                }
            }
        }
        request.setAttribute(WebConstant.ORDER_USER, SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId));
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/shop/cart.jsp");
        rd.forward(request, response);
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
        ProductCommand command = FormUtil.populate(ProductCommand.class, request);
        Integer userId = (Integer) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.USER_ID);
        OrderDTO orderDTO = (OrderDTO) SessionUtil.getInstance().getValue(request.getSession(), WebConstant.ORDER + userId);
        if (orderDTO != null) {
            for (ItemDTO item : orderDTO.getItemDTOS()) {
                for (int i = 0; i < command.getQuantity().length; i++) {
                    if (item.getProductDTO().getProductId().equals(Integer.parseInt(command.getProductIdList()[i]))) {
                        item.setQuantity(Integer.parseInt(command.getQuantity()[i]));
                    }
                }
            }
            response.sendRedirect("/cart.html?crudaction=redirect_update");
        } else {
            response.sendRedirect("/cart.html?crudaction=redirect_error");
        }
    }
}

