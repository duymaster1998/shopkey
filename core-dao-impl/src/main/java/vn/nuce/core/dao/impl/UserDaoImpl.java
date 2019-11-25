package vn.nuce.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.nuce.core.common.utils.HibernateUtil;
import vn.nuce.core.dao.UserDao;
import vn.nuce.core.data.daoimpl.AbstractDao;
import vn.nuce.core.persistence.entity.UserEntity;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {
    public Object[] checkLogin(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        boolean isUserExist = false;
        String roleName = null;
        UserEntity userEntity = new UserEntity();
        try {
            StringBuilder sql = new StringBuilder("FROM UserEntity ue WHERE ue.userName= :userName AND ue.password= :password");
            Query query = session.createQuery(sql.toString());
            query.setParameter("userName", name);
            query.setParameter("password", password);
            if (query.list().size() > 0) {
                isUserExist = true;
                userEntity = (UserEntity) query.uniqueResult();
                roleName = userEntity.getRoleEntity().getRoleName();
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{isUserExist, roleName, userEntity};
    }
}
