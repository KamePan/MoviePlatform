package cn.edu.ecnu.model.response;

import cn.edu.ecnu.model.dataobject.ActorDO;
import cn.edu.ecnu.model.dataobject.DirectorDO;
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

    List<String> composer;

    List<String> category;

    List<String> district;

    List<String> language;

    Integer showtime;

    Integer length;

    String desc;

    List<String> othername;

    List<ActorDO> actor;

    List<DirectorDO> director;

}
