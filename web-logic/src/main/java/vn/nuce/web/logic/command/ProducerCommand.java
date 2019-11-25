package vn.nuce.web.logic.command;

import vn.nuce.core.dto.ProducerDTO;
import vn.nuce.core.web.command.AbstractCommand;

public class ProducerCommand extends AbstractCommand<ProducerDTO> {
    public ProducerCommand() {
        this.pojo = new ProducerDTO();
    }
}
