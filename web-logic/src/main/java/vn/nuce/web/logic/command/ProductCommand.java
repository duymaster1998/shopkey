package vn.nuce.web.logic.command;

import vn.nuce.core.dto.CategoryDTO;
import vn.nuce.core.dto.ProducerDTO;
import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.web.command.AbstractCommand;

import java.util.List;

public class ProductCommand extends AbstractCommand<ProductDTO> {
    private List<CategoryDTO> categoryDTOList;
    private List<ProducerDTO> producerDTOList;
    private String dir;
    private String searchName;
    private Integer categoryId;
    private Integer producerId;
    private String productImage;
    private String[] quantity;
    private String[] productIdList;
    private Integer productDetailQty;

    public Integer getProductDetailQty() {
        return productDetailQty;
    }

    public void setProductDetailQty(Integer productDetailQty) {
        this.productDetailQty = productDetailQty;
    }

    public String[] getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(String[] productIdList) {
        this.productIdList = productIdList;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public ProductCommand() {
        this.pojo = new ProductDTO();
    }

    public List<CategoryDTO> getCategoryDTOList() {
        return categoryDTOList;
    }

    public void setCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        this.categoryDTOList = categoryDTOList;
    }

    public List<ProducerDTO> getProducerDTOList() {
        return producerDTOList;
    }

    public void setProducerDTOList(List<ProducerDTO> producerDTOList) {
        this.producerDTOList = producerDTOList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }
}
