package vn.nuce.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.nuce.core.common.utils.HibernateUtil;
import vn.nuce.core.dao.ProductDetailDao;
import vn.nuce.core.data.daoimpl.AbstractDao;
import vn.nuce.core.persistence.entity.ProductDetailEntity;
import vn.nuce.core.persistence.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailDaoImpl extends AbstractDao<Integer, ProductDetailEntity> implements ProductDetailDao {
    public Object[] findAllProductDetailByProductId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProductDetailEntity> entities = new ArrayList<ProductDetailEntity>();
        boolean isListExist = false;
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ProductDetailEntity pe WHERE pe.productEntity.productId= :productId");
            Query query = session.createQuery(sql.toString());
            query.setParameter("productId", id);
            if (query.list().size() > 0) {
                isListExist = true;
                entities = query.list();
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[] {isListExist,entities};
    }

    public List<ProductDetailEntity> selectTopProductDetail(Integer id, Integer quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProductDetailEntity> entities = new ArrayList<ProductDetailEntity>();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ProductDetailEntity pe WHERE pe.productEntity.productId= :productId");
            sql.append(" AND pe.keyStatus= :keyStatus");
            Query query = session.createQuery(sql.toString());
            query.setParameter("productId", id);
            query.setParameter("keyStatus",false);
            if (quantity != null) {
                query.setMaxResults(quantity);
            }
            entities = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entities;
    }

}
