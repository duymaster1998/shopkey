package vn.nuce.web.logic.command;

import vn.nuce.core.dto.RoleDTO;
import vn.nuce.core.dto.UserDTO;
import vn.nuce.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
    private String repeatPass;
    private String confirmPassword;
    private String newPassword;
    private String currentPassword;
    private String newPhoneNumber;
    private Integer amount;
    private RoleDTO dto;
    private Integer roleId;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }

    public UserCommand() {
        this.pojo = new UserDTO();
    }

    public String getRepeatPass() {
        return repeatPass;
    }

    public void setRepeatPass(String repeatPass) {
        this.repeatPass = repeatPass;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public RoleDTO getDto() {
        return dto;
    }

    public void setDto(RoleDTO dto) {
        this.dto = dto;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
