package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {

    void insertBatchUsers(List<UserDO> userDOS);

    List<UserDO> selectUserByUsername(String username);

    void insertUser(UserDO userDO);

    void updateUserById(UserDO userDO);

    UserDO selectUserById(Integer id);
}
