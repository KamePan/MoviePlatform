package cn.edu.ecnu.model.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieDO {

    Integer id;

    String title;

    Double rate;

    String url;

    String cover;

    String director;

    String composer;

    String actor;

    String category;

    String district;

    String language;

    Integer showtime;

    Integer length;

    String othername;
}
