package cn.edu.ecnu.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {

    String username;

    String password;

    Integer gender;

    Date birthday;

    String email;

}
