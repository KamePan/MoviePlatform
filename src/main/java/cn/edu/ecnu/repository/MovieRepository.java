package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.MovieDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieRepository {

    MovieDO selectMovieById(Integer id);

    List<MovieDO> selectMovieByTitle(String title);

    void insertBatchMovies(List<MovieDO> movies);

    List<MovieDO> selectAllMovies();

    List<MovieDO> selectMovieSelective(MovieDO movieDO, Integer offset, Integer pageSize);

    List<MovieDO> selectMovieByScoreDesc(Integer size);
}
