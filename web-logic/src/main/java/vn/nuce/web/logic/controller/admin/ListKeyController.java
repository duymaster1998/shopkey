package vn.nuce.web.logic.controller.admin;

import vn.nuce.core.dto.ProductDetailDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.web.logic.command.ProductDetailCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin-list-key.html")
public class ListKeyController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDetailCommand command = FormUtil.populate(ProductDetailCommand.class, request);
        if (command.getUrlType().equals(WebConstant.URL_KEY)) {
            Integer productId = Integer.parseInt(command.getProductId());
            List<ProductDetailDTO> productDetailDTOList = (List<ProductDetailDTO>) SingletonServiceUtil.getProductDetailServiceInstance().findAllProductDetailById(productId)[1];
            command.setListResult(productDetailDTOList);
        } else if (command.getUrlType().equals(WebConstant.URL_LIST)) {
            command.setListResult(SingletonServiceUtil.getProductDetailServiceInstance().findAllProductDetail());
        }
        request.setAttribute(WebConstant.LIST_ITEMS, command.getListResult());
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/key/list_key.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
