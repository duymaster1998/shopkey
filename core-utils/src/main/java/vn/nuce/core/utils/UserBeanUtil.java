package vn.nuce.core.utils;

import vn.nuce.core.dto.UserDTO;
import vn.nuce.core.persistence.entity.UserEntity;

public class UserBeanUtil {
    public static UserDTO entityToDto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setBalance(entity.getBalance());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setFirstName(entity.getFirstName());
        dto.setHomeTown(entity.getHomeTown());
        dto.setLastName(entity.getLastName());
        dto.setPassword(entity.getPassword());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());
        dto.setRoleDTO(RoleBeanUtil.entityToDto(entity.getRoleEntity()));
        return dto;
    }

    public static UserEntity dtoToEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setBalance(dto.getBalance());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setFirstName(dto.getFirstName());
        entity.setHomeTown(dto.getHomeTown());
        entity.setLastName(dto.getLastName());
        entity.setPassword(dto.getPassword());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setUserId(dto.getUserId());
        entity.setUserName(dto.getUserName());
        entity.setRoleEntity(RoleBeanUtil.dtoToEntity(dto.getRoleDTO()));
        return entity;
    }
}
