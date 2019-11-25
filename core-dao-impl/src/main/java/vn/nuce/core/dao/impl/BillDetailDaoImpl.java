package vn.nuce.core.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.nuce.core.common.utils.HibernateUtil;
import vn.nuce.core.dao.BillDetailDao;
import vn.nuce.core.data.daoimpl.AbstractDao;
import vn.nuce.core.persistence.entity.BillDetailEntity;
import vn.nuce.core.persistence.entity.BillEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BillDetailDaoImpl extends AbstractDao<Integer, BillDetailEntity> implements BillDetailDao {

}
