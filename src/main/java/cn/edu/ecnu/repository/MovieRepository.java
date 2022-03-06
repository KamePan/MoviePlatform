package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieRepository {

    MovieDO selectMovieById(Integer id);

    List<MovieDO> selectMovieByTitle(String title);

    void insertBatchMovies(List<MovieDO> movies);

}
