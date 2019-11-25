package vn.nuce.web.logic.controller.admin;

import org.apache.log4j.Logger;
import vn.nuce.core.dto.CategoryDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.web.logic.command.CategoryCommand;
import vn.nuce.web.logic.command.ProducerCommand;
import vn.nuce.web.logic.command.ProductDetailCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet("/admin-list-category.html")
public class CategoryProducerListController extends HttpServlet {
    private transient final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryCommand categoryCommand = FormUtil.populate(CategoryCommand.class,request);
        ProducerCommand producerCommand = FormUtil.populate(ProducerCommand.class,request);
        categoryCommand.setListResult(SingletonServiceUtil.getCategoryServiceInstance().findAllCategory());
        request.setAttribute(WebConstant.LIST_ITEMS,categoryCommand.getListResult());
        producerCommand.setListResult(SingletonServiceUtil.getProducerServiceInstance().findAllProducer());
        request.setAttribute(WebConstant.LIST_ITEMS_PRODUCER,producerCommand.getListResult());
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/category/list-category.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryCommand categoryCommand = FormUtil.populate(CategoryCommand.class,request);
        ProducerCommand producerCommand = FormUtil.populate(ProducerCommand.class,request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        String categoryName = request.getParameter("categoryName");
        byte[] bytes = categoryName.getBytes(StandardCharsets.ISO_8859_1);
        categoryName = new String(bytes, StandardCharsets.UTF_8);
        CategoryDTO dto = categoryCommand.getPojo();
        dto.setCategoryName(categoryName);
        try {
            if (dto != null) {
                SingletonServiceUtil.getCategoryServiceInstance().saveCategory(dto);
                request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
                request.setAttribute(WebConstant.MESSAGE_RESPONSE,resourceBundle.getString("label.guideline.listen.add.success"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE,resourceBundle.getString("label.error"));
        }
        categoryCommand.setListResult(SingletonServiceUtil.getCategoryServiceInstance().findAllCategory());
        request.setAttribute(WebConstant.LIST_ITEMS,categoryCommand.getListResult());
        producerCommand.setListResult(SingletonServiceUtil.getProducerServiceInstance().findAllProducer());
        request.setAttribute(WebConstant.LIST_ITEMS_PRODUCER,producerCommand.getListResult());
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/category/list-category.jsp");
        rd.forward(request, response);
    }
}
