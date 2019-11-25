package vn.nuce.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "productdetail")
public class ProductDetailEntity {
    @Id
    @Column(name = "productkey")
    private String productKey;

    @Column(name = "keystatus")
    private boolean keyStatus;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "exportdate")
    private Timestamp exportDate;

    @ManyToOne
    @JoinColumn(name = "productid")
    private ProductEntity productEntity;

    @OneToMany(mappedBy = "productDetailEntity",fetch = FetchType.LAZY)
    private List<BillDetailEntity> billDetailEntityList;

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

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public List<BillDetailEntity> getBillDetailEntityList() {
        return billDetailEntityList;
    }

    public void setBillDetailEntityList(List<BillDetailEntity> billDetailEntityList) {
        this.billDetailEntityList = billDetailEntityList;
    }
}
