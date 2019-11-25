package vn.nuce.core.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "billdetail")
public class BillDetailEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "productkey")
    private ProductDetailEntity productDetailEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "billid")
    private BillEntity billEntity;

    @Column(name = "price")
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductDetailEntity getProductDetailEntity() {
        return productDetailEntity;
    }

    public void setProductDetailEntity(ProductDetailEntity productDetailEntity) {
        this.productDetailEntity = productDetailEntity;
    }

    public BillEntity getBillEntity() {
        return billEntity;
    }

    public void setBillEntity(BillEntity billEntity) {
        this.billEntity = billEntity;
    }
}