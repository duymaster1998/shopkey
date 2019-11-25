package vn.nuce.core.utils;

import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.persistence.entity.ProductEntity;

public class ProductBeanUtil {
    public static ProductDTO entityToDto(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(entity.getProductId());
        dto.setCategoryDTO(CategoryBeanUtil.entityToDto(entity.getCategoryEntity()));
        dto.setProducerDTO(ProducerBeanUtil.entityToDto(entity.getProducerEntity()));
        dto.setProductDescription(entity.getProductDescription());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setPrice(entity.getPrice());
        dto.setProductName(entity.getProductName());
        return dto;
    }

    public static ProductEntity dtoToEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setProductId(dto.getProductId());
        entity.setCategoryEntity(CategoryBeanUtil.dtoToEntity(dto.getCategoryDTO()));
        entity.setImage(dto.getImage());
        entity.setContent(dto.getContent());
        entity.setProductDescription(dto.getProductDescription());
        entity.setPrice(dto.getPrice());
        entity.setProducerEntity(ProducerBeanUtil.dtoToEntity(dto.getProducerDTO()));
        entity.setProductName(dto.getProductName());
        return entity;
    }
}
