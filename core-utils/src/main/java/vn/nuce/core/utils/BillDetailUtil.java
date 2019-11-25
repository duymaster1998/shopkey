package vn.nuce.core.utils;

import vn.nuce.core.dto.BillDetailDTO;
import vn.nuce.core.persistence.entity.BillDetailEntity;

public class BillDetailUtil {
    public static BillDetailDTO entityToDto(BillDetailEntity entity) {
        BillDetailDTO dto = new BillDetailDTO();
        dto.setBillDTO(BillBeanUtil.entityToDto(entity.getBillEntity()));
        dto.setPrice(entity.getPrice());
        dto.setProductDetailDTO(ProductDetailBeanUtil.entityToDto(entity.getProductDetailEntity()));
        return dto;
    }

    public static BillDetailEntity dtoToEntity(BillDetailDTO dto) {
        BillDetailEntity entity = new BillDetailEntity();
        entity.setProductDetailEntity(ProductDetailBeanUtil.dtoToEntity(dto.getProductDetailDTO()));
        entity.setBillEntity(BillBeanUtil.dtoToEntity(dto.getBillDTO()));
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
