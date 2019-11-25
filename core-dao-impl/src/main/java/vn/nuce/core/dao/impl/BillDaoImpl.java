package vn.nuce.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.nuce.core.common.utils.HibernateUtil;
import vn.nuce.core.dao.BillDao;
import vn.nuce.core.data.daoimpl.AbstractDao;
import vn.nuce.core.persistence.entity.BillEntity;

public class BillDaoImpl extends AbstractDao<Integer, BillEntity> implements BillDao {
    public Integer findBillByEsc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        BillEntity entity = new BillEntity();
        Integer billId;
        try {
            StringBuilder sql = new StringBuilder("FROM BillEntity ORDER BY createdDate DESC");
            Query query = session.createQuery(sql.toString());
            query.setMaxResults(1);
            entity = (BillEntity) query.list().get(0);
            billId = entity.getBillId();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return billId;
    }
}
