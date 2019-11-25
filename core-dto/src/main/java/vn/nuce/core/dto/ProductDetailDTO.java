package vn.nuce.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ProductDetailDTO implements Serializable {
    private String productKey;
    private boolean keyStatus;
    private Timestamp createdDate;
    private Timestamp exportDate;
    private ProductDTO productDTO;

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public boolean isKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(boolean keyStatus) {
        this.keyStatus = keyStatus;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getExportDate() {
        return exportDate;
    }

    public void setExportDate(Timestamp exportDate) {
        this.exportDate = exportDate;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

}
