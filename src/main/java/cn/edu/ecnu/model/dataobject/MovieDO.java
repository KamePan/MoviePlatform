package cn.edu.ecnu.model.dataobject;

import lombok.Data;

import java.util.List;

@Data
public class MovieDO {

    Integer id;

    String title;

    Double rate;

    String url;

    String cover;

    String composer;

    String category;

    String district;

    String language;

    Integer showtime;

    Integer length;

    String othername;

    String desc;

    List<ActorDO> actorDOS;

    List<DirectorDO> directorDOS;
}
