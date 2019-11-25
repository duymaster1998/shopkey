package vn.nuce.web.logic.command;

import vn.nuce.core.dto.ProductDetailDTO;
import vn.nuce.core.web.command.AbstractCommand;

public class ProductDetailCommand extends AbstractCommand<ProductDetailDTO> {
    private String productId;
    private String categoryId;
    private String producerId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductDetailCommand() {
        this.pojo = new ProductDetailDTO();
    }
}
