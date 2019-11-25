package vn.nuce.core.dto;

import java.io.Serializable;
import java.util.List;

public class ProductDTO implements Serializable {
    private Integer productId;
    private String productName;
    private Integer price;
    private String content;
    private String image;
    private String productDescription;
    private CategoryDTO categoryDTO;
    private ProducerDTO producerDTO;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public ProducerDTO getProducerDTO() {
        return producerDTO;
    }

    public void setProducerDTO(ProducerDTO producerDTO) {
        this.producerDTO = producerDTO;
    }

}

