package vn.nuce.core.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "producer")
public class ProducerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer producerId;

    @Column(name = "producername")
    private String producerName;

    @OneToMany(mappedBy = "producerEntity", fetch = FetchType.LAZY)
    private List<ProductEntity> productEntityList;

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
