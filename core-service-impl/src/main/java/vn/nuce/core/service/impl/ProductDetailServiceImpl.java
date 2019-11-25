package vn.nuce.core.service.impl;

import vn.nuce.core.dto.CheckOutResultDTO;
import vn.nuce.core.dto.ProductDetailDTO;
import vn.nuce.core.persistence.entity.ProductDetailEntity;
import vn.nuce.core.service.ProductDetailService;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.utils.ProductDetailBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailServiceImpl implements ProductDetailService {
    public void saveProductDetail(ProductDetailDTO detailDTO) {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        detailDTO.setCreatedDate(createDate);
        ProductDetailEntity entity = ProductDetailBeanUtil.dtoToEntity(detailDTO);
        SingletonDaoUtil.getProductDetailDaoInstance().save(entity);
    }

    public Object[] findAllProductDetailById(Integer var1) {
        List<ProductDetailDTO> productDetailDTOList = new ArrayList<ProductDetailDTO>();
        Object[] objects = SingletonDaoUtil.getProductDetailDaoInstance().findAllProductDetailByProductId(var1);
        boolean isListExist = (Boolean) objects[0];
        List<ProductDetailEntity> productDetailEntities = (List<ProductDetailEntity>) objects[1];
        for(ProductDetailEntity item : productDetailEntities) {
            productDetailDTOList.add(ProductDetailBeanUtil.entityToDto(item));
        }
        return new Object[]{isListExist,productDetailDTOList};
    }

    public List<ProductDetailDTO> findAllProductDetail() {
        List<ProductDetailDTO> productDetailDTOList = new ArrayList<ProductDetailDTO>();
        List<ProductDetailEntity> entities = SingletonDaoUtil.getProductDetailDaoInstance().findAll();
        for(ProductDetailEntity item : entities) {
            productDetailDTOList.add(ProductDetailBeanUtil.entityToDto(item));
        }
        return productDetailDTOList;
    }

    public List<ProductDetailDTO> selectTopProductDetail(Integer id,Integer quantity) {
        List<ProductDetailDTO> productDetailDTOList = new ArrayList<ProductDetailDTO>();
        List<ProductDetailEntity> entities = SingletonDaoUtil.getProductDetailDaoInstance().selectTopProductDetail(id,quantity);
        for(ProductDetailEntity item : entities) {
            productDetailDTOList.add(ProductDetailBeanUtil.entityToDto(item));
        }
        return productDetailDTOList;
    }

    public ProductDetailDTO updateProductDetail(ProductDetailDTO detailDTO) {
        Timestamp exportDate = new Timestamp(System.currentTimeMillis());
        detailDTO.setExportDate(exportDate);
        detailDTO.setKeyStatus(true);
        ProductDetailEntity entity = ProductDetailBeanUtil.dtoToEntity(detailDTO);
        entity = SingletonDaoUtil.getProductDetailDaoInstance().update(entity);
        return ProductDetailBeanUtil.entityToDto(entity);
    }

}
