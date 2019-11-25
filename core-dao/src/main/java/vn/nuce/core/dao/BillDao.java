package vn.nuce.core.dao;

import vn.nuce.core.data.dao.GenericDao;
import vn.nuce.core.persistence.entity.BillEntity;

public interface BillDao extends GenericDao<Integer, BillEntity> {
    Integer findBillByEsc();

}
