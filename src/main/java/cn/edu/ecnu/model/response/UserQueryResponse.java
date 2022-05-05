package cn.edu.ecnu.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserQueryResponse {

    Integer id;

    String username;

    Integer gender;

    String email;

    Date birthday;

    String desc;

    String role;

    String avatar;

}
