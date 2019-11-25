package vn.nuce.core.service.impl;

import vn.nuce.core.dto.CheckLoginDTO;
import vn.nuce.core.dto.UserDTO;
import vn.nuce.core.persistence.entity.UserEntity;
import vn.nuce.core.service.UserService;
import vn.nuce.core.service.utils.SingletonDaoUtil;
import vn.nuce.core.utils.UserBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public CheckLoginDTO checkLogin(String name, String password) {
        CheckLoginDTO checkLogin = new CheckLoginDTO();
        if (name != null && password != null) {
            Object[] objects = SingletonDaoUtil.getUserDaoInstance().checkLogin(name, password);
            checkLogin.setUserExist((Boolean) objects[0]);
            if (checkLogin.isUserExist()) {
                checkLogin.setRoleName((String) objects[1]);
                checkLogin.setUserDTO(UserBeanUtil.entityToDto((UserEntity) objects[2]));
            }
        }
        return checkLogin;
    }

    public List<UserDTO> findAllUser() {
        List<UserEntity> entities = SingletonDaoUtil.getUserDaoInstance().findAll();
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (UserEntity item : entities) {
            userDTOList.add(UserBeanUtil.entityToDto(item));
        }
        return userDTOList;
    }

    public void saveUser(UserDTO dto) {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(createDate);
        dto.setBalance(0);
        UserEntity entity = UserBeanUtil.dtoToEntity(dto);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    public UserDTO findById(Integer id) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findById(id);
        return UserBeanUtil.entityToDto(entity);
    }

    public UserDTO updateUser(UserDTO dto) {
        UserEntity entity = UserBeanUtil.dtoToEntity(dto);
        entity = SingletonDaoUtil.getUserDaoInstance().update(entity);
        return UserBeanUtil.entityToDto(entity);
    }

}
