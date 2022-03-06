package cn.edu.ecnu.service;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.repository.MovieRepository;
import cn.edu.ecnu.repository.RateRepository;
import cn.edu.ecnu.util.Neo4jWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RateRepository rateRepository;

    public Movie queryMovieInfoByMovieId(Integer id) {
        MovieDO movieDO = movieRepository.selectMovieById(id);
        Movie movie = MovieConvertor.convertDOToEntity(movieDO);
        return movie;
    }

    /**
     * 查询 Neo4j 获取用户的评分过的电影，得到电影的 title, 再从 MySQL 中查询电影的详细信息
     * @param id
     * @return
     */
    public List<Movie> queryRatingMoviesByUserId(Integer id) {
        Neo4jWrapper neo4jWrapper = Neo4jWrapper.newInstance();
        List<RateDO> rateDOS = neo4jWrapper.queryRatingMovieByUserId(id);
        List<Movie> movies = new ArrayList<>();
        for (RateDO rateDO: rateDOS) {
            /* 这里很奇怪，字符串中自带 " 双引号，而查询 mysql 中相应 title 字段并不带引号*/
            String title = rateDO.getTitle().replaceAll("\"", "");
            List<MovieDO> MovieDOSWithInfo = movieRepository.selectMovieByTitle(title);
            if (MovieDOSWithInfo.size() != 0) {
                Movie movie = MovieConvertor.convertDOToEntity(MovieDOSWithInfo.get(0));
                movies.add(movie);
            }
        }
        return movies;
    }
}
