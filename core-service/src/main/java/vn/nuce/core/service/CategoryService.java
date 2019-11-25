package vn.nuce.core.service;

import vn.nuce.core.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAllCategory();
    void saveCategory(CategoryDTO dto);
}
