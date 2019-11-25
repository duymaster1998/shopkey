package vn.nuce.core.dto;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {
    private Integer id;
    private UserDTO userDTO;
    private List<ItemDTO> itemDTOS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<ItemDTO> getItemDTOS() {
        return itemDTOS;
    }

    public void setItemDTOS(List<ItemDTO> itemDTOS) {
        this.itemDTOS = itemDTOS;
    }
}
