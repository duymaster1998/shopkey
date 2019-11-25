package vn.nuce.core.dao;

import vn.nuce.core.data.dao.GenericDao;
import vn.nuce.core.persistence.entity.ProductDetailEntity;
import vn.nuce.core.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Map;

public interface ProductDao extends GenericDao<Integer, ProductEntity> {
    //    List<ProductDetailEntity> findAllProductDetail(Integer var1);
    List<ProductEntity> findProductByProperties(Integer productId, Integer producerId, Integer categoryId, Integer limit);

    List<ProductEntity> findProductByName(String name);

    List<ProductEntity> findAllProductByPriceAscOrDesc(String sortDirection);

    List<ProductEntity> findAllProduct(Map<String,Object> property,Integer offset, Integer limit, String sortDirection);
}
