package vn.nuce.core.service;

import vn.nuce.core.dto.CheckLoginDTO;
import vn.nuce.core.dto.UserDTO;

import java.util.List;

public interface UserService {
    CheckLoginDTO checkLogin(String name, String password);
    List<UserDTO> findAllUser();
    void saveUser(UserDTO dto);
    UserDTO findById(Integer id);
    UserDTO updateUser(UserDTO dto);
}
