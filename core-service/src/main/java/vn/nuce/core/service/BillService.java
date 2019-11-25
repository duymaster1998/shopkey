package vn.nuce.core.service;

import vn.nuce.core.dto.BillDTO;

import java.util.List;

public interface BillService {
    Integer findBillByDesc();
    void saveBill(BillDTO dto);
    List<BillDTO> findAllBill();
}
