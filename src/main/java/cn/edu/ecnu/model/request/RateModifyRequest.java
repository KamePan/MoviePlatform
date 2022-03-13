package cn.edu.ecnu.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RateModifyRequest {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("user_id")
    Integer userId;

    @JsonProperty("movie_id")
    Integer movieId;

    @JsonProperty("rate")
    Double rate;

    @JsonProperty("comment")
    String comment;

}
