package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.model.dataobject.StatisticDO;
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

    @Select("select r.id as r_id, m.id as m_id, r.rate as r_rate, m.rate as m_rate, " +
            "url, cover, title, showtime, length, category, language, " +
            "othername, district, `desc`, rate_time, comment, user_id " +
            "from rate as r left outer join movie as m on r.movie_id = m.id " +
            "where r.user_id = #{id}")
    @Results(id = "RateBasicResultMap", value = {
            @Result(property = "id", column = "r_id"),
            @Result(property = "rate", column = "r_rate"),
            @Result(property = "rateTime", column = "rate_time"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "movieDO.id", column = "m_id"),
            @Result(property = "movieDO.url", column = "url"),
            @Result(property = "movieDO.cover", column = "cover"),
            @Result(property = "movieDO.title", column = "title"),
            @Result(property = "movieDO.rate", column = "m_rate"),
            @Result(property = "movieDO.showtime", column = "showtime"),
            @Result(property = "movieDO.length", column = "length"),
            @Result(property = "movieDO.director", column = "director"),
            @Result(property = "movieDO.composer", column = "composer"),
            @Result(property = "movieDO.actor", column = "actor"),
            @Result(property = "movieDO.category", column = "category"),
            @Result(property = "movieDO.language", column = "language"),
            @Result(property = "movieDO.othername", column = "othername"),
            @Result(property = "movieDO.district", column = "district"),
            @Result(property = "movieDO.desc", column = "desc")
    })
    List<RateDO> selectRateByUserId(Integer id);

    @Select("select r.id as r_id, m.id as m_id, r.rate as r_rate, m.rate as m_rate, " +
            "url, cover, title, showtime, length, category, language, " +
            "othername, district, `desc`, rate_time, comment, user_id " +
            "from rate as r left outer join movie as m on r.movie_id = m.id " +
            "where r.movie_id = #{id}")
    @ResultMap("RateBasicResultMap")
    List<RateDO> selectRateByMovieId(Integer id);

    @Select("select r.id as r_id, m.id as m_id, r.rate as r_rate, m.rate as m_rate, " +
            "url, cover, title, showtime, length, category, language, " +
            "othername, district, `desc`, rate_time, comment, user_id " +
            "from rate as r left outer join movie as m on r.movie_id = m.id " +
            "where r.user_id = #{userId} and r.movie_id = #{movieId}")
    @ResultMap("RateBasicResultMap")
    List<RateDO> selectRateByUserAndMovieId(Integer userId, Integer movieId);

    @Insert("insert into rate(id, user_id, movie_id, rate, comment, rate_time) " +
            "values(#{id}, #{userId}, #{movieDO.id}, #{rate}, #{comment}, #{rateTime})")
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
    StatisticDO selectStatisticByUserId(Integer id);
}
