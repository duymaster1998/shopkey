package vn.nuce.core.service.impl;

import vn.nuce.core.dto.ProductDTO;
import vn.nuce.core.dto.ProductDetailDTO;
import vn.nuce.core.persistence.entity.ProductDetailEntity;
import vn.nuce.core.persistence.entity.ProductEntity;
import vn.nuce.core.service.ProductService;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.utils.ProductBeanUtil;
import vn.nuce.core.utils.ProductDetailBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    public List<ProductDTO> findAllProduct(Map<String,Object> property,Integer offset, Integer limit, String sortDirection) {
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        List<ProductEntity> entities = SingletonDaoUtil.getProductDaoInstance().findAllProduct(property,offset, limit, sortDirection);
        for (ProductEntity item : entities) {
            ProductDTO dto = ProductBeanUtil.entityToDto(item);
            list.add(dto);
        }
        return list;
    }

    public List<ProductDTO> findProductByProductName(String name) {
        List<ProductEntity> entities = SingletonDaoUtil.getProductDaoInstance().findProductByName(name);
        List<ProductDTO> dtoList = new ArrayList<ProductDTO>();
        for(ProductEntity item : entities) {
            dtoList.add(ProductBeanUtil.entityToDto(item));
        }
        return dtoList;
    }

    public void saveProduct(ProductDTO dto) {
        ProductEntity entity = ProductBeanUtil.dtoToEntity(dto);
        SingletonDaoUtil.getProductDaoInstance().save(entity);
    }

    public ProductDTO findById(Integer id) {
        ProductEntity entity = SingletonDaoUtil.getProductDaoInstance().findById(id);
        return ProductBeanUtil.entityToDto(entity);
    }

    public ProductDTO updateProduct(ProductDTO dto) {
        ProductEntity entity = ProductBeanUtil.dtoToEntity(dto);
        entity = SingletonDaoUtil.getProductDaoInstance().update(entity);
        return ProductBeanUtil.entityToDto(entity);
    }

    public List<ProductDTO> findProductByProperties(Integer productId, Integer producerId, Integer categoryId, Integer limit) {
        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        List<ProductEntity> entityList = SingletonDaoUtil.getProductDaoInstance().findProductByProperties(productId, producerId, categoryId, limit);
        for (ProductEntity item : entityList) {
            productDTOList.add(ProductBeanUtil.entityToDto(item));
        }
        return productDTOList;
    }

    public List<ProductDTO> findProductByPriceAscOrDesc(String sortDirection) {
        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        List<ProductEntity> productEntityList = SingletonDaoUtil.getProductDaoInstance().findAllProductByPriceAscOrDesc(sortDirection);
        for (ProductEntity item : productEntityList) {
            productDTOList.add(ProductBeanUtil.entityToDto(item));
        }
        return productDTOList;
    }

    public Integer deleteProduct(List<Integer> ids) {
        return SingletonDaoUtil.getProductDaoInstance().delete(ids);
    }

    public Object getTotalItem() {
        return SingletonDaoUtil.getProductDaoInstance().countItem();
    }

//    public List<ProductDetailDTO> findAllProductDetail(Integer productId) {
//        List<ProductDetailDTO> productDetailDTOList = new ArrayList<ProductDetailDTO>();
//        List<ProductDetailEntity> productDetailEntityList = SingletonDaoUtil.getProductDaoInstance().findAllProductDetail(productId);
//        for (ProductDetailEntity item : productDetailEntityList) {
//            productDetailDTOList.add(ProductDetailBeanUtil.entityToDto(item));
//        }
//        return productDetailDTOList;
//    }
}
