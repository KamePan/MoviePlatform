package cn.edu.ecnu.model.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO {

    Integer id;

    String username;

    String password;

    Integer gender;

    String email;

    Date birthday;

    String desc;

    String role;
}
