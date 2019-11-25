package vn.nuce.core.service.impl;

import vn.nuce.core.dto.BillDetailDTO;
import vn.nuce.core.dto.CheckOutResultDTO;
import vn.nuce.core.persistence.entity.BillDetailEntity;
import vn.nuce.core.persistence.entity.ProductDetailEntity;
import vn.nuce.core.service.BillDetailService;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.utils.BillDetailUtil;

import java.util.ArrayList;
import java.util.List;

public class BillDetailServiceImpl implements BillDetailService {
    public void save(BillDetailDTO detailDTO) {
        SingletonDaoUtil.getBillDetailDaoInstance().save(BillDetailUtil.dtoToEntity(detailDTO));
    }

    public List<CheckOutResultDTO> findById(Integer id) {
        List<CheckOutResultDTO> dtos = new ArrayList<CheckOutResultDTO>();
        List<BillDetailEntity> entities = SingletonDaoUtil.getBillDetailDaoInstance().findAll();
        List<BillDetailEntity> temp = new ArrayList<BillDetailEntity>();
        for (BillDetailEntity item : entities) {
            if (item.getBillEntity().getBillId().equals(id)) {
                temp.add(item);
            }
        }
        for (BillDetailEntity item : temp) {
            CheckOutResultDTO dto = new CheckOutResultDTO();
            dto.setKey(item.getProductDetailEntity().getProductKey());
            dto.setName(item.getProductDetailEntity().getProductEntity().getProductName());
            dto.setPrice(item.getPrice());
            dtos.add(dto);
        }
        return dtos;
    }
}
