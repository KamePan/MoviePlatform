package cn.edu.ecnu.model.response;

import lombok.Data;

@Data
public class RateQueryResponse {

    Integer id;

    Integer userId;

    Double rate;

    String comment;

    MovieQueryResponse movieQueryResponse;
}
