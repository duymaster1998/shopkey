package vn.nuce.core.dto;

import java.io.Serializable;
import java.util.List;

public class ProducerDTO implements Serializable {
    private Integer producerId;
    private String producerName;

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
