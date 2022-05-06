package cn.edu.ecnu.service;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.request.MovieSearchRequest;
import cn.edu.ecnu.repository.MovieRepository;
import cn.edu.ecnu.repository.RateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
//        Neo4jWrapper neo4jWrapper = Neo4jWrapper.newInstance();
//        List<RateDO> rateDOS = neo4jWrapper.queryRatingMovieByUserId(id);
//        List<Movie> movies = new ArrayList<>();
//        for (RateDO rateDO: rateDOS) {
//            /* 这里很奇怪，字符串中自带 " 双引号，而查询 mysql 中相应 title 字段并不带引号*/
//            String title = rateDO.getTitle().replaceAll("\"", "");
//            List<MovieDO> MovieDOSWithInfo = movieRepository.selectMovieByTitle(title);
//            if (MovieDOSWithInfo.size() != 0) {
//                Movie movie = MovieConvertor.convertDOToEntity(MovieDOSWithInfo.get(0));
//                movies.add(movie);
//            }
//        }
//        return movies;
        return new ArrayList<>();
    }

    /**
     * 开启进程执行 python 程序获取推荐列表
     */
    public List<Movie> queryRecommendMovieListByUserId(Integer id) {
        String PYTHON3_PATH = "/Users/pankaiming/miniforge3/envs/tf_mac/bin/python";
        String PYTHON_SCRIPT_PATH = "/Users/pankaiming/PycharmProjects/MovieRecommendSystem/main.py";
        try {
            Process process = Runtime.getRuntime().exec(String.join(" ", PYTHON3_PATH, PYTHON_SCRIPT_PATH));
            System.out.println(process.waitFor());
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String title = null;
            List<Movie> movies = new ArrayList<>();
            while ((title = in.readLine()) != null) {
                System.out.println(title);
                List<MovieDO> movieDOS = movieRepository.selectMovieByTitle(title);
                Movie movie = MovieConvertor.convertDOToEntity(movieDOS.get(0));
                movies.add(movie);
            }
            return movies;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Movie> pageQueryMovieSelective(MovieSearchRequest movieSearchRequest) {
        Integer pageSize = movieSearchRequest.getPageSize();
        Integer pageNumber = movieSearchRequest.getPageNumber();
        Integer offset = (pageNumber - 1) * pageSize;
        MovieDO movieDO = new MovieDO();
        BeanUtils.copyProperties(movieSearchRequest, movieDO);
        List<MovieDO> movieDOS = movieRepository.selectMovieSelective(movieDO, offset, pageSize);
        List<Movie> movies = movieDOS.stream()
                .map(MovieConvertor::convertDOToEntity)
                .collect(Collectors.toList());
        return movies;
    }

    public List<Movie> queryHighScoreMovieList(Integer size) {
        List<MovieDO> movieDOS = movieRepository.selectMovieByScoreDesc(size);
        List<Movie> movies = movieDOS.stream()
                .limit(size)
                .map(MovieConvertor::convertDOToEntity)
                .collect(Collectors.toList());
        return movies;
    }
}
