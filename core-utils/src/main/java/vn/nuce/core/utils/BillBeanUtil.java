package vn.nuce.core.utils;

import vn.nuce.core.dto.BillDTO;
import vn.nuce.core.persistence.entity.BillEntity;

public class BillBeanUtil {
    public static BillDTO entityToDto(BillEntity entity) {
        BillDTO dto = new BillDTO();
        dto.setBillId(entity.getBillId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setTotalMoney(entity.getTotalMoney());
        dto.setUserDTO(UserBeanUtil.entityToDto(entity.getUserEntity()));
        return dto;
    }

    public static BillEntity dtoToEntity(BillDTO dto) {
        BillEntity entity = new BillEntity();
        entity.setBillId(dto.getBillId());
        entity.setUserEntity(UserBeanUtil.dtoToEntity(dto.getUserDTO()));
        entity.setTotalMoney(dto.getTotalMoney());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}
