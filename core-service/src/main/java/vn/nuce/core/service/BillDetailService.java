package vn.nuce.core.service;

import vn.nuce.core.dto.BillDetailDTO;
import vn.nuce.core.dto.CheckOutResultDTO;

import java.util.List;

public interface BillDetailService {
    void save(BillDetailDTO detailDTO);
    List<CheckOutResultDTO> findById(Integer id);
}
