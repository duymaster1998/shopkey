package vn.nuce.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.nuce.core.common.constant.CoreConstant;
import vn.nuce.core.common.utils.HibernateUtil;
import vn.nuce.core.dao.ProductDao;
import vn.nuce.core.data.daoimpl.AbstractDao;
import vn.nuce.core.persistence.entity.ProductDetailEntity;
import vn.nuce.core.persistence.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl extends AbstractDao<Integer, ProductEntity> implements ProductDao {
    public List<ProductEntity> findProductByProperties(Integer productId, Integer producerId, Integer categoryId, Integer limit) {
        List<ProductEntity> entityList = new ArrayList<ProductEntity>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ProductEntity pe WHERE pe.producerEntity.producerId= :producerId");
            sql.append(" AND pe.categoryEntity.categoryId= :categoryId");
            sql.append(" AND pe.productId!= :productId");
            Query query = session.createQuery(sql.toString());
            query.setParameter("producerId", producerId);
            query.setParameter("categoryId", categoryId);
            query.setParameter("productId", productId);
            if (limit != null && limit > 0) {
                query.setMaxResults(limit);
            }
            entityList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entityList;
    }

    public List<ProductEntity> findProductByName(String name) {
        List<ProductEntity> entityList = new ArrayList<ProductEntity>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ProductEntity");
            if (name != null) {
                sql.append(" WHERE productName LIKE :productName");
            }
            Query query = session.createQuery(sql.toString());
            query.setParameter("productName", "%" + name + "%");
            entityList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entityList;
    }

    public List<ProductEntity> findAllProductByPriceAscOrDesc(String sortDirection) {
        List<ProductEntity> entityList = new ArrayList<ProductEntity>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ProductEntity ");
            if (sortDirection != null) {
                //sql.append(" ORDER BY price ");
                //sql.append(sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc");
            }
            Query query = session.createQuery(sql.toString());
            entityList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entityList;
    }

    public List<ProductEntity> findAllProduct(Map<String, Object> property, Integer offset, Integer limit, String sortDirection) {
        List<ProductEntity> entityList = new ArrayList<ProductEntity>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i = 0;
        for (Map.Entry item : property.entrySet()) {
            params[i] = (String) item.getKey();
            values[i] = item.getValue();
            ++i;
        }
        try {
            StringBuilder sql = new StringBuilder("FROM ProductEntity");
            if (property.size() > 0) {
                for (int i1 = 0; i1 < params.length; i1++) {
                    if (i1 == 0) {
                        if (params[i1].equals("productName")) {
                            sql.append(" WHERE ").append(params[i1]).append(" LIKE :" + params[i1] + "");
                        } else
                            sql.append(" WHERE ").append(params[i1]).append("= :" + params[i1] + "");
                    } else {
                        if (params[i1].equals("productName")) {
                            sql.append(" AND ").append(params[i1]).append(" LIKE :" + params[i1] + "");
                        } else
                        sql.append(" AND ").append(params[i1]).append("= :" + params[i1] + "");
                    }
                }
            }
            if (sortDirection != null) {
                sql.append(" ORDER BY price ");
                sql.append(sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc");
            }
            Query query = session.createQuery(sql.toString());
            if (property.size() > 0) {
                for (int i2 = 0; i2 < params.length; i2++) {
                    if (params[i2].equals("productName")) {
                        query.setParameter(params[i2], "%" + values[i2] + "%");
                    } else
                        query.setParameter(params[i2], values[i2]);
                }
            }
            if (offset != null && limit != null) {
                query.setFirstResult(offset);
                query.setMaxResults(limit);
            }
            entityList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entityList;
    }
}
