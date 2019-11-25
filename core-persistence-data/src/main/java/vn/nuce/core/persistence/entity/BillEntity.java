package vn.nuce.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "totalmoney")
    private Integer totalMoney;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "billEntity", fetch = FetchType.LAZY)
    private List<BillDetailEntity> billDetailEntityList;

    public List<BillDetailEntity> getBillDetailEntityList() {
        return billDetailEntityList;
    }

    public void setBillDetailEntityList(List<BillDetailEntity> billDetailEntityList) {
        this.billDetailEntityList = billDetailEntityList;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
