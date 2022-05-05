package cn.edu.ecnu.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserModifyRequest {

    Integer id;

    Integer gender;

    Date birthday;

    String email;

    String desc;

    String avatar;
}
