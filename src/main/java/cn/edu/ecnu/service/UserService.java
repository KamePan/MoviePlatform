package cn.edu.ecnu.service;

import cn.edu.ecnu.convertor.UserConvertor;
import cn.edu.ecnu.model.dataobject.UserDO;
import cn.edu.ecnu.model.entity.User;
import cn.edu.ecnu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(User user) {
        UserDO userDO = UserConvertor.convertEntityToDO(user);
        userRepository.insertUser(userDO);
    }

    public void modifyUserInfo(User user) {
        UserDO userDO = UserConvertor.convertEntityToDO(user);
        userRepository.updateUserById(userDO);
    }

    public User queryUserById(Integer id) {
        UserDO userDO = userRepository.selectUserById(id);
        return UserConvertor.convertDOToEntity(userDO);
    }
}
