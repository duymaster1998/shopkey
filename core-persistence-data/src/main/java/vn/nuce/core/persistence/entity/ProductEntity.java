package vn.nuce.core.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "productname")
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "productdescription")
    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "producerid")
    private ProducerEntity producerEntity;

    @OneToMany(mappedBy = "productEntity",fetch = FetchType.LAZY)
    private List<ProductDetailEntity> productDetailEntityList;

    public List<ProductDetailEntity> getProductDetailEntityList() {
        return productDetailEntityList;
    }

    public void setProductDetailEntityList(List<ProductDetailEntity> productDetailEntityList) {
        this.productDetailEntityList = productDetailEntityList;
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

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public ProducerEntity getProducerEntity() {
        return producerEntity;
    }

    public void setProducerEntity(ProducerEntity producerEntity) {
        this.producerEntity = producerEntity;
    }
}