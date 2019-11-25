package vn.nuce.web.logic.controller.web;

import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.web.logic.command.ProductCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/product-detail.html")
public class ProductDetailController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductCommand command = FormUtil.populate(ProductCommand.class,request);
        List<ProductDTO> dtoList = SingletonServiceUtil.getProductServiceInstance().findProductByProperties(command.getPojo().getProductId(),command.getProducerId(),command.getCategoryId(),5);
        for (ProductDTO item : dtoList) {
            item.setQuantity(SingletonServiceUtil.getProductDetailServiceInstance().selectTopProductDetail(item.getProductId(),null).size());
        }
        request.setAttribute(WebConstant.LIST_ITEMS,dtoList);
        ProductDTO pojo = SingletonServiceUtil.getProductServiceInstance().findById(command.getPojo().getProductId());
        pojo.setQuantity(SingletonServiceUtil.getProductDetailServiceInstance().selectTopProductDetail(command.getPojo().getProductId(),null).size());
        request.setAttribute(WebConstant.FROM_ITEM, pojo);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/shop/product-detail.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
