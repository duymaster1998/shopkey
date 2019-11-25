package vn.nuce.core.utils;

import vn.nuce.core.dto.CategoryDTO;
import vn.nuce.core.persistence.entity.CategoryEntity;

public class CategoryBeanUtil {
    public static CategoryDTO entityToDto(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(entity.getCategoryId());
        dto.setCategoryName(entity.getCategoryName());
        return dto;
    }

    public static CategoryEntity dtoToEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryId(dto.getCategoryId());
        entity.setCategoryName(dto.getCategoryName());
        return entity;
    }
}
