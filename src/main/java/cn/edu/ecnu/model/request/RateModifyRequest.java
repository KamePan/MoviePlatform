package cn.edu.ecnu.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RateModifyRequest {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("userId")
    Integer userId;

    @JsonProperty("movieId")
    Integer movieId;

    @JsonProperty("rate")
    Double rate;

    @JsonProperty("comment")
    String comment;

}
