package cn.edu.ecnu.model.request;

import lombok.Data;

import java.util.List;

@Data
public class MovieInsertRequest {

    Integer id;

    String title;

    Double rate;

    String url;

    String cover;

    List<String> director;

    List<String> composer;

    List<String> actor;

    List<String> category;

    List<String> district;

    List<String> language;

    Integer showtime;

    Integer length;

    List<String> othername;

    String desc;
}
