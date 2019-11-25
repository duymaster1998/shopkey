package vn.nuce.web.logic.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import vn.nuce.core.common.utils.SessionUtil;
import vn.nuce.core.common.utils.UploadUtil;
import vn.nuce.core.dto.CategoryDTO;
import vn.nuce.core.dto.ProducerDTO;
import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.dto.ProductDetailDTO;
import vn.nuce.core.web.common.WebConstant;
import vn.nuce.core.web.utils.FormUtil;
import vn.nuce.core.web.utils.SingletonServiceUtil;
import vn.nuce.core.web.utils.WebCommonUtil;
import vn.nuce.web.logic.command.ProductCommand;
import vn.nuce.web.logic.command.ProductDetailCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-home.html", "/admin-add-product.html", "/ajax-edit-key.html"})
public class AdminController extends HttpServlet {
    private transient final Logger log = Logger.getLogger(this.getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductCommand command = FormUtil.populate(ProductCommand.class, request);
        ProductDTO pojo = command.getPojo();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        if (request.getSession() != null) {
            if (SessionUtil.getInstance().getValue(request.getSession(), WebConstant.LOGIN_NAME) != null) {
                SessionUtil.getInstance().getValue(request.getSession(), WebConstant.LOGIN_NAME);
                if (command.getUrlType().equals(WebConstant.URL_LIST)) {
                    Map<String, String> mapMess = buildMapMessage(resourceBundle);
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMess);
                    command.setListResult(SingletonServiceUtil.getProductServiceInstance().findAllProduct(new HashMap<String, Object>(),null,null,null));
                    request.setAttribute(WebConstant.LIST_ITEMS, command.getListResult());
                    RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
                    rd.forward(request, response);
                } else if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                    if (command.getCrudaction().equals(WebConstant.PRODUCT_UPDATE)) {
                        if (pojo != null && pojo.getProductId() != null) {
                            command.setPojo(SingletonServiceUtil.getProductServiceInstance().findById(pojo.getProductId()));
                            request.setAttribute(WebConstant.FROM_ITEM, command);
                        }
                    }
                    command.setCategoryDTOList(SingletonServiceUtil.getCategoryServiceInstance().findAllCategory());
                    command.setProducerDTOList(SingletonServiceUtil.getProducerServiceInstance().findAllProducer());
                    request.setAttribute(WebConstant.CATEGORYS, command.getCategoryDTOList());
                    request.setAttribute(WebConstant.PRODUCERS, command.getProducerDTOList());
                    RequestDispatcher rd = request.getRequestDispatcher("/views/admin/add_or_edit_product.jsp");
                    rd.forward(request, response);
                } else if (command.getUrlType().equals(WebConstant.URL_KEY)) {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    ProducerDTO producerDTO = new ProducerDTO();
                    producerDTO.setProducerId(command.getProducerId());
                    categoryDTO.setCategoryId(command.getCategoryId());
                    pojo.setProducerDTO(producerDTO);
                    pojo.setCategoryDTO(categoryDTO);
                    request.setAttribute(WebConstant.FROM_ITEM, pojo);
                    RequestDispatcher rd = request.getRequestDispatcher("/views/admin/key/edit.jsp");
                    rd.forward(request, response);
                }
            } else {
                response.sendRedirect("/login.html?action=login");
            }

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
        String urlType = request.getParameter("urlType");
        if (urlType.equals(WebConstant.URL_LIST)) {
            ProductCommand command = FormUtil.populate(ProductCommand.class, request);
            List<Integer> ids = new ArrayList<Integer>();
            List<Integer> integerList = new ArrayList<Integer>();
            for (String item : command.getCheckList()) {
                ids.add(Integer.parseInt(item));
            }
            for (Integer item : ids) {
                boolean checkList = (Boolean) SingletonServiceUtil.getProductDetailServiceInstance().findAllProductDetailById(item)[0];
                if(!checkList) {
                    integerList.add(item);
                }
            }
            if (integerList.size() == ids.size()) {
                Integer result = SingletonServiceUtil.getProductServiceInstance().deleteProduct(integerList);
                if (result != integerList.size()) {
                    response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_error");
                }
                response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_delete");
            } else {
                response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_error");
            }
        } else if (urlType.equals(WebConstant.URL_KEY)) {
            ProductDetailCommand command = FormUtil.populate(ProductDetailCommand.class, request);
            ProductDetailDTO detailDTO = command.getPojo();
            ProductDTO dto = new ProductDTO();
            ProducerDTO producerDTO = new ProducerDTO();
            CategoryDTO categoryDTO = new CategoryDTO();
            producerDTO.setProducerId(Integer.parseInt(command.getProducerId()));
            categoryDTO.setCategoryId(Integer.parseInt(command.getCategoryId()));
            dto.setProductId(Integer.parseInt(command.getProductId()));
            dto.setCategoryDTO(categoryDTO);
            dto.setProducerDTO(producerDTO);
            detailDTO.setProductDTO(dto);
            try {
                SingletonServiceUtil.getProductDetailServiceInstance().saveProductDetail(detailDTO);
                response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_insert");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_error");
            }
        } else {
            ProductCommand command = new ProductCommand();
            UploadUtil uploadUtil = new UploadUtil();
            Set<String> valueTitle = buildSetValueListenGuideLine();
            Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle, WebConstant.LISTENGUIDELINE);
            boolean checkStatusUploadImage = (Boolean) objects[0];
            if (!checkStatusUploadImage) {
                response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_error");
            } else {
                ProductDTO dto = command.getPojo();
                if (StringUtils.isNotBlank(objects[2].toString())) {
                    dto.setImage(objects[2].toString());
                }
                Map<String, String> mapValue = (Map<String, String>) objects[3];
                if (objects[2].toString().equals("listenguideline" + File.separator)) {
                    dto.setImage(mapValue.get("productImage"));
                }
                dto = returnValueDTO(dto, mapValue);
                try {
                    if (dto.getProductId() != null) {
                        SingletonServiceUtil.getProductServiceInstance().updateProduct(dto);
                        response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_update");
                    } else {
                        SingletonServiceUtil.getProductServiceInstance().saveProduct(dto);
                        response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_insert");
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    response.sendRedirect("/admin-home.html?urlType=url_list&&crudaction=redirect_error");
                }
            }
        }
    }

    private ProductDTO returnValueDTO(ProductDTO dto, Map<String, String> mapValue) {
        for (Map.Entry<String, String> item : mapValue.entrySet()) {
            if (item.getKey().equals("pojo.productName")) {
                dto.setProductName(item.getValue());
            } else if (item.getKey().equals("pojo.content")) {
                dto.setContent(item.getValue());
            } else if (item.getKey().equals("pojo.price")) {
                dto.setPrice(Integer.parseInt(item.getValue()));
            } else if (item.getKey().equals("pojo.productDescription")) {
                dto.setProductDescription(item.getValue());
            } else if (item.getKey().equals("categoryId")) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryId(Integer.parseInt(item.getValue()));
                dto.setCategoryDTO(categoryDTO);
            } else if (item.getKey().equals("producerId")) {
                ProducerDTO producerDTO = new ProducerDTO();
                producerDTO.setProducerId(Integer.parseInt(item.getValue()));
                dto.setProducerDTO(producerDTO);
            } else if (item.getKey().equals("pojo.productId")) {
                dto.setProductId(Integer.parseInt(item.getValue()));
            }
        }
        return dto;
    }

    private Set<String> buildSetValueListenGuideLine() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.productName");
        returnValue.add("pojo.productId");
        returnValue.add("pojo.content");
        returnValue.add("pojo.price");
        returnValue.add("pojo.productDescription");
        returnValue.add("categoryId");
        returnValue.add("producerId");
        returnValue.add("productImage");
        return returnValue;
    }

}
