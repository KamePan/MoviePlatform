package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.ActDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActRepository {

    void insertBatchAct(List<ActDO> ActDOS);

}
