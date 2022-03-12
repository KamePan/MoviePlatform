package cn.edu.ecnu.model.request;

import lombok.Data;

@Data
public class RateInsertRequest {

    Integer userId;

    Integer movieId;

    Double rate;

    String comment;

}
