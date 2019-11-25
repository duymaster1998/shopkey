package vn.nuce.core.utils;

import vn.nuce.core.dto.ProducerDTO;
import vn.nuce.core.persistence.entity.ProducerEntity;

public class ProducerBeanUtil {
    public static ProducerDTO entityToDto(ProducerEntity entity) {
        ProducerDTO dto = new ProducerDTO();
        dto.setProducerId(entity.getProducerId());
        dto.setProducerName(entity.getProducerName());
        return dto;
    }

    public static ProducerEntity dtoToEntity(ProducerDTO dto) {
        ProducerEntity entity = new ProducerEntity();
        entity.setProducerId(dto.getProducerId());
        entity.setProducerName(entity.getProducerName());
        return entity;
    }
}
