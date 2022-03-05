package cn.edu.ecnu.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Movie {

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
}
