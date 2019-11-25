package vn.nuce.core.dao;

import vn.nuce.core.data.dao.GenericDao;
import vn.nuce.core.persistence.entity.ProductDetailEntity;
import vn.nuce.core.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductDetailDao extends GenericDao<Integer, ProductDetailEntity> {
    Object[] findAllProductDetailByProductId(Integer id);
    List<ProductDetailEntity> selectTopProductDetail(Integer id,Integer quantity);
}
