package cn.edu.ecnu.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class RateQueryResponse {

    Integer id;

    Integer userId;

    Double rate;

    Date rateTime;

    String comment;

    UserQueryResponse user;

    MovieQueryResponse movie;
}
