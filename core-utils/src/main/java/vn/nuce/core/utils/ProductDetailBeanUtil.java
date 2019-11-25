package vn.nuce.core.utils;

import vn.nuce.core.dto.ProductDetailDTO;
import vn.nuce.core.persistence.entity.ProductDetailEntity;

public class ProductDetailBeanUtil {
    public static ProductDetailDTO entityToDto(ProductDetailEntity entity) {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductKey(entity.getProductKey());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setExportDate(entity.getExportDate());
        dto.setKeyStatus(entity.isKeyStatus());
        dto.setProductDTO(ProductBeanUtil.entityToDto(entity.getProductEntity()));
        return dto;
    }

    public static ProductDetailEntity dtoToEntity(ProductDetailDTO dto) {
        ProductDetailEntity entity = new ProductDetailEntity();
        entity.setProductKey(dto.getProductKey());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setExportDate(dto.getExportDate());
        entity.setKeyStatus(dto.isKeyStatus());
        entity.setProductEntity(ProductBeanUtil.dtoToEntity(dto.getProductDTO()));
        return entity;
    }
}
