package cn.edu.ecnu.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    Integer id;

    String username;

    String password;

    Integer gender;

    String email;

    Date birthday;

    String desc;

    String role;

}
