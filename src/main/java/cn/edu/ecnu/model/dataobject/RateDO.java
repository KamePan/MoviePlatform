package cn.edu.ecnu.model.dataobject;

import cn.edu.ecnu.model.entity.Movie;
import lombok.Data;

import java.util.Date;

@Data
public class RateDO {

    Integer id;

    Double rate;

    String comment;

    Date rateTime;

    Integer userId;

    Integer movieDO;
}
