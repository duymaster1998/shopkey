package vn.nuce.web.logic.command;

import vn.nuce.core.dto.CategoryDTO;
import vn.nuce.core.web.command.AbstractCommand;

public class CategoryCommand extends AbstractCommand<CategoryDTO> {
    public CategoryCommand() {
        this.pojo = new CategoryDTO();
    }
}
