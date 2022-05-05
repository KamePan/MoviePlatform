package cn.edu.ecnu.convertor;

import cn.edu.ecnu.model.dataobject.UserDO;
import cn.edu.ecnu.model.entity.User;
import cn.edu.ecnu.model.request.UserModifyRequest;
import cn.edu.ecnu.model.request.UserRegisterRequest;
import cn.edu.ecnu.model.response.UserQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserConvertor {

    public static User convertRequestToEntity(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterRequest, user);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(userRegisterRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        return user;
    }

    public static User convertRequestToEntity(UserModifyRequest userModifyRequest) {
        User user = new User();
        BeanUtils.copyProperties(userModifyRequest, user);
        return user;
    }

//    public static UserQueryResponse convertEntityToResponse(User user) {
//        UserQueryResponse userQueryResponse = new UserQueryResponse();
//        BeanUtils.copyProperties(user, userQueryResponse);
//        return userQueryResponse;
//    }

    public static User convertDOToEntity(UserDO userDO) {
        User user = new User();
        BeanUtils.copyProperties(userDO, user);
        return user;
    }

    public static UserDO convertEntityToDO(User user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        return userDO;
    }


    public static UserQueryResponse convertEntityToResponse(User user) {
        UserQueryResponse userQueryResponse = new UserQueryResponse();
        BeanUtils.copyProperties(user, userQueryResponse);
        return userQueryResponse;
    }
}
