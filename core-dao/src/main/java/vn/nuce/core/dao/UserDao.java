package vn.nuce.core.dao;

import vn.nuce.core.data.dao.GenericDao;
import vn.nuce.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    Object[] checkLogin(String name,String password);
}
