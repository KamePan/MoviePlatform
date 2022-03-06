package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.RateDO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends Neo4jRepository<RateDO, Integer> {

    @Query("MATCH (u:User{id:\'440\'})-[r:RATED]-(m:Movie)\n" +
            "WITH id(r) AS id, m.title AS title, r.grading AS rate\n" +
            "RETURN id, title, rate")
    List<RateDO>  selectRatingMovieByUserId(@Param("id") Integer id);

}
