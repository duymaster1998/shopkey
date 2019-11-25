package vn.nuce.web.logic.command;

import vn.nuce.core.dto.BillDTO;
import vn.nuce.core.web.command.AbstractCommand;

public class BillCommand extends AbstractCommand<BillDTO> {
    private Integer earningsMonth;
    private Integer earningsYear;

    public Integer getEarningsMonth() {
        return earningsMonth;
    }

    public void setEarningsMonth(Integer earningsMonth) {
        this.earningsMonth = earningsMonth;
    }

    public Integer getEarningsYear() {
        return earningsYear;
    }

    public void setEarningsYear(Integer earningsYear) {
        this.earningsYear = earningsYear;
    }

    public BillCommand() {
        this.pojo = new BillDTO();
    }
}
