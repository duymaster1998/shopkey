package vn.nuce.core.web.utils;

import vn.nuce.core.service.impl.*;

public class SingletonServiceUtil {
    private static UserServiceImpl userServiceImpl = null;
    private static ProducerServiceImpl producerServiceImpl = null;
    private static ProductServiceImpl productServiceImpl = null;
    private static CategoryServiceImpl categoryServiceImpl = null;
    private static ProductDetailServiceImpl productDetailServiceImpl = null;
    private static BillServiceImpl billServiceImpl = null;
    private static BillDetailServiceImpl billDetailServiceImpl = null;

    public static UserServiceImpl getUserServiceInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }


    public static ProducerServiceImpl getProducerServiceInstance() {
        if (producerServiceImpl == null) {
            producerServiceImpl = new ProducerServiceImpl();
        }
        return producerServiceImpl;
    }

    public static ProductServiceImpl getProductServiceInstance() {
        if (productServiceImpl == null) {
            productServiceImpl = new ProductServiceImpl();
        }
        return productServiceImpl;
    }

    public static CategoryServiceImpl getCategoryServiceInstance() {
        if (categoryServiceImpl == null) {
            categoryServiceImpl = new CategoryServiceImpl();
        }
        return categoryServiceImpl;
    }

    public static ProductDetailServiceImpl getProductDetailServiceInstance() {
        if (productDetailServiceImpl == null) {
            productDetailServiceImpl = new ProductDetailServiceImpl();
        }
        return productDetailServiceImpl;
    }

    public static BillServiceImpl getBillServiceInstance() {
        if (billServiceImpl == null) {
            billServiceImpl = new BillServiceImpl();
        }
        return billServiceImpl;
    }

    public static BillDetailServiceImpl getBillDetailServiceInstance() {
        if (billDetailServiceImpl == null) {
            billDetailServiceImpl = new BillDetailServiceImpl();
        }
        return billDetailServiceImpl;
    }
}
