package vn.nuce.core.dto;

import java.io.Serializable;

public class BillDetailDTO implements Serializable {
    private ProductDetailDTO productDetailDTO;
    private BillDTO billDTO;
    private Integer price;

    public ProductDetailDTO getProductDetailDTO() {
        return productDetailDTO;
    }

    public void setProductDetailDTO(ProductDetailDTO productDetailDTO) {
        this.productDetailDTO = productDetailDTO;
    }

    public BillDTO getBillDTO() {
        return billDTO;
    }

    public void setBillDTO(BillDTO billDTO) {
        this.billDTO = billDTO;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

