package cn.edu.ecnu.model.request;

import lombok.Data;

@Data
public class MovieSearchRequest {

    String title;

    String district;

    String category;

    String language;

    Integer pageNumber;

    Integer pageSize;
}
