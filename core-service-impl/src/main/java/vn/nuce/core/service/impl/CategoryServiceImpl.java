package vn.nuce.core.service.impl;

import vn.nuce.core.dto.CategoryDTO;
import vn.nuce.core.persistence.entity.CategoryEntity;
import vn.nuce.core.service.CategoryService;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.utils.CategoryBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    public List<CategoryDTO> findAllCategory() {
        List<CategoryDTO> list = new ArrayList<CategoryDTO>();
        List<CategoryEntity> entities = SingletonDaoUtil.getCategoryDaoInstance().findAll();
        for(CategoryEntity item : entities) {
            CategoryDTO dto = CategoryBeanUtil.entityToDto(item);
            list.add(dto);
        }
        return list;
    }

    public void saveCategory(CategoryDTO dto) {
        CategoryEntity entity = CategoryBeanUtil.dtoToEntity(dto);
        SingletonDaoUtil.getCategoryDaoInstance().save(entity);
    }
}
