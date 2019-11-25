package vn.nuce.core.service.utils;

import vn.nuce.core.dao.impl.*;

public class SingletonDaoUtil {
    private static UserDaoImpl userDaoImpl = null;
    private static RoleDaoImpl roleDaoImpl = null;
    private static ProducerDaoImpl producerDaoImpl = null;
    private static ProductDaoImpl productDaoImpl = null;
    private static CategoryDaoImpl categoryDaoImpl = null;
    private static ProductDetailDaoImpl productDetailDaoImpl = null;
    private static BillDaoImpl billDaoImpl = null;
    private static BillDetailDaoImpl billDetailDaoImpl = null;

    public static UserDaoImpl getUserDaoInstance() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }

    public static RoleDaoImpl getRoleDaoInstance() {
        if (roleDaoImpl == null) {
            roleDaoImpl = new RoleDaoImpl();
        }
        return roleDaoImpl;
    }

    public static ProducerDaoImpl getProducerDaoInstance() {
        if (producerDaoImpl == null) {
            producerDaoImpl = new ProducerDaoImpl();
        }
        return producerDaoImpl;
    }

    public static ProductDaoImpl getProductDaoInstance() {
        if (productDaoImpl == null) {
            productDaoImpl = new ProductDaoImpl();
        }
        return productDaoImpl;
    }

    public static CategoryDaoImpl getCategoryDaoInstance() {
        if (categoryDaoImpl == null) {
            categoryDaoImpl = new CategoryDaoImpl();
        }
        return categoryDaoImpl;
    }

    public static ProductDetailDaoImpl getProductDetailDaoInstance() {
        if (productDetailDaoImpl == null) {
            productDetailDaoImpl = new ProductDetailDaoImpl();
        }
        return productDetailDaoImpl;
    }

    public static BillDaoImpl getBillDaoInstance() {
        if (billDaoImpl == null) {
            billDaoImpl = new BillDaoImpl();
        }
        return billDaoImpl;
    }

    public static BillDetailDaoImpl getBillDetailDaoInstance() {
        if (billDetailDaoImpl == null) {
            billDetailDaoImpl = new BillDetailDaoImpl();
        }
        return billDetailDaoImpl;
    }
}
