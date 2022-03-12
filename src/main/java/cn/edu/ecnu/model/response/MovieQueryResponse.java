package cn.edu.ecnu.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class MovieQueryResponse implements Serializable {

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
