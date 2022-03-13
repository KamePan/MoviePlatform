package cn.edu.ecnu.model.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class RateDO {

    Integer id;

    Double rate;

    String comment;

    Date rateTime;

    Integer userId;

    Integer movieId;
}
