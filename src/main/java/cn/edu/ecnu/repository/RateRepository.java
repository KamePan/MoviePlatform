package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.model.dataobject.StatDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RateRepository {

    /*@Query("MATCH (u:User{id:\'440\'})-[r:RATED]-(m:Movie)\n" +
            "WITH id(r) AS id, m.title AS title, r.grading AS rate\n" +
            "RETURN id, title, rate")
    List<RateDO>  selectRatingMovieByUserId(@Param("id") Integer id);
*/

    @Select("select id, rate, rate_time, comment, user_id, movie_id " +
            "from rate " +
            "where user_id = #{id}")
    @Results(id = "RateBasicResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "rate", column = "rate"),
            @Result(property = "rateTime", column = "rate_time"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "movieId", column = "movie_id")
    })
    List<RateDO> selectRateByUserId(Integer id);

    @Select("select id, rate, rate_time, comment, user_id, movie_id " +
            "from rate " +
            "where movie_id = #{id}")
    @ResultMap("RateBasicResultMap")
    List<RateDO> selectRateByMovieId(Integer id);

    @Select("select id, rate, rate_time, comment, user_id, movie_id " +
            "from rate " +
            "where user_id = #{userId} and movie_id = #{movieId}")
    @ResultMap("RateBasicResultMap")
    List<RateDO> selectRateByUserAndMovieId(Integer userId, Integer movieId);

    @Insert("insert into rate(id, user_id, movie_id, rate, comment, rate_time) " +
            "values(#{id}, #{userId}, #{movieId}, #{rate}, #{comment}, #{rateTime})")
    void insertRate(RateDO rate);

    @Update("update rate " +
            "set rate = #{rate}, comment = #{comment}, rate_time = #{rateTime} " +
            "where id = #{id}")
    void updateRate(RateDO rate);

    @Delete("delete from rate where id = #{id}")
    void deleteRate(Integer id);

    @Select("select count(*) as rate_cnt, avg(rate) as avg_rate " +
            "from rate " +
            "where user_id = #{id} ")
    @Results(id = "RateStatisticResultMap", value = {
            @Result(property = "rateCnt", column = "rate_cnt"),
            @Result(property = "avgRate", column = "avg_rate"),
    })
    StatDO selectStatisticByUserId(Integer id);
}
