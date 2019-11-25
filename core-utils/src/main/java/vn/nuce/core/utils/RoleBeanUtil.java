package vn.nuce.core.utils;

import vn.nuce.core.dto.RoleDTO;
import vn.nuce.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
    public static RoleDTO entityToDto(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        return dto;
    }

    public static RoleEntity dtoToEntity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setRoleName(dto.getRoleName());
        return entity;
    }
}
