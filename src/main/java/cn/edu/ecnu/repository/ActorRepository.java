package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.ActorDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActorRepository {

    void insertBatchActors(List<ActorDO> actorDOS);

    List<ActorDO> selectActorByName(String name);

}
