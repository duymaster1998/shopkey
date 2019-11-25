package vn.nuce.web.logic.controller.web;

import org.apache.commons.lang.StringUtils;
import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.web.logic.command.CategoryCommand;
import vn.nuce.web.logic.command.ProducerCommand;
import vn.nuce.web.logic.command.ProductCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/shopping-cart.html")
public class ShoppingCartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryCommand categoryCommand = FormUtil.populate(CategoryCommand.class, request);
        ProducerCommand producerCommand = FormUtil.populate(ProducerCommand.class, request);
        ProductCommand productCommand = FormUtil.populate(ProductCommand.class, request);
        Map<String,Object> objects = new HashMap<String,Object>();
        Integer offset = (productCommand.getPage() - 1) * productCommand.getMaxPageItems();
        if (productCommand.getDir().equals(WebConstant.SORT_DEFAULT)) {
            productCommand.setListResult(SingletonServiceUtil.getProductServiceInstance().findAllProduct(objects,offset, productCommand.getMaxPageItems(),null));
            request.setAttribute(WebConstant.CHOSE, "default");
        } else if (productCommand.getDir().equals(WebConstant.SORT_ASC)) {
            productCommand.setListResult(SingletonServiceUtil.getProductServiceInstance().findAllProduct(objects,offset, productCommand.getMaxPageItems(),"1"));
            request.setAttribute(WebConstant.CHOSE, "asc");
        } else if (productCommand.getDir().equals(WebConstant.SORT_DESC)) {
            productCommand.setListResult(SingletonServiceUtil.getProductServiceInstance().findAllProduct(objects,offset, productCommand.getMaxPageItems(),"2"));
            request.setAttribute(WebConstant.CHOSE, "desc");
        }
        for (ProductDTO item : productCommand.getListResult()) {
            item.setQuantity(SingletonServiceUtil.getProductDetailServiceInstance().selectTopProductDetail(item.getProductId(),null).size());
        }
        Object total = SingletonServiceUtil.getProductServiceInstance().getTotalItem();
        productCommand.setTotalItem(Integer.parseInt(total.toString()));
        productCommand.setTotalPage((int) Math.ceil((double) productCommand.getTotalItem() / productCommand.getMaxPageItems()));
        request.setAttribute(WebConstant.LIST_ITEMS_PRODUCT, productCommand);
        categoryCommand.setListResult(SingletonServiceUtil.getCategoryServiceInstance().findAllCategory());
        request.setAttribute(WebConstant.LIST_ITEMS, categoryCommand.getListResult());
        producerCommand.setListResult(SingletonServiceUtil.getProducerServiceInstance().findAllProducer());
        request.setAttribute(WebConstant.LIST_ITEMS_PRODUCER, producerCommand.getListResult());
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/shop/shopping-cart.jsp");
        rd.forward(request, response);
    }

    private Map<String, Object> buildPropertyMap(ProductCommand productCommand) {
        Map<String,Object> objects = new HashMap<String,Object>();
        if (StringUtils.isNotBlank(productCommand.getSearchName())) {
            objects.put("productName",productCommand.getSearchName());
        } else if (productCommand.getCategoryId() != null) {
//            objects.put("")
        }
        return objects;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
