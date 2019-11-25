package vn.nuce.core.service.impl;

import vn.nuce.core.dto.BillDTO;
import vn.nuce.core.persistence.entity.BillEntity;
import vn.nuce.core.service.BillService;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.utils.BillBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {
    public Integer findBillByDesc() {
        return SingletonDaoUtil.getBillDaoInstance().findBillByEsc();
    }

    public void saveBill(BillDTO dto) {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(createDate);
        SingletonDaoUtil.getBillDaoInstance().save(BillBeanUtil.dtoToEntity(dto));
    }

    public List<BillDTO> findAllBill() {
        List<BillDTO> dtos = new ArrayList<BillDTO>();
        List<BillEntity> entities = SingletonDaoUtil.getBillDaoInstance().findAll();
        for (BillEntity item : entities) {
            dtos.add(BillBeanUtil.entityToDto(item));
        }
        return dtos;
    }
}
