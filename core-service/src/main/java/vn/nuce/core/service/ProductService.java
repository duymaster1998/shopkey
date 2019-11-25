package vn.nuce.core.service;

import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.dto.ProductDetailDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDTO> findAllProduct(Map<String,Object> property,Integer offset, Integer limit, String sortDirection);

    List<ProductDTO> findProductByProductName(String name);

    void saveProduct(ProductDTO dto);

    ProductDTO findById(Integer id);

    ProductDTO updateProduct(ProductDTO dto);

    List<ProductDTO> findProductByProperties(Integer productId, Integer producerId, Integer categoryId, Integer limit);

    List<ProductDTO> findProductByPriceAscOrDesc(String sortDirection);

    Integer deleteProduct(List<Integer> ids);

    Object getTotalItem();
//    List<ProductDetailDTO> findAllProductDetail(Integer productId);
}
