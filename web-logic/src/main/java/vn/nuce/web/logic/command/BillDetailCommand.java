package vn.nuce.web.logic.command;

import vn.nuce.core.dto.BillDetailDTO;
import vn.nuce.core.web.command.AbstractCommand;

public class BillDetailCommand extends AbstractCommand<BillDetailDTO> {
    public BillDetailCommand() {
        this.pojo = new BillDetailDTO();
    }
}
