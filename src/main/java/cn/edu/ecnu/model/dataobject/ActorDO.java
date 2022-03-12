package cn.edu.ecnu.model.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class ActorDO {

    Integer id;

    String name;

    String nationality;

    Integer gender;

    String desc;

    Date birthday;
}
