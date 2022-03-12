package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.ActDO;
import cn.edu.ecnu.model.dataobject.DirectDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DirectRepository {

    void insertBatchDirect(List<DirectDO> directDOS);

}
