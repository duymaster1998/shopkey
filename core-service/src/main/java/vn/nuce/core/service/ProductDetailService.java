package vn.nuce.core.service;

import vn.nuce.core.dto.CheckOutResultDTO;
import vn.nuce.core.dto.ProductDetailDTO;

import java.util.List;

public interface ProductDetailService {
    void saveProductDetail(ProductDetailDTO detailDTO);
    Object[] findAllProductDetailById(Integer var1);
    List<ProductDetailDTO> findAllProductDetail();
    List<ProductDetailDTO> selectTopProductDetail(Integer id,Integer quantity);
    ProductDetailDTO updateProductDetail(ProductDetailDTO detailDTO);
}
