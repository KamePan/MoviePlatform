package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.ActorDO;
import cn.edu.ecnu.model.dataobject.DirectorDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DirectorRepository {

    void insertBatchDirectors(List<DirectorDO> directorDOS);

    List<DirectorDO> selectDirectorByName(String name);

}
