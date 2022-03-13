package cn.edu.ecnu.service;

import cn.edu.ecnu.model.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void queryRecommendMovieListByUserIdTest() {
        List<Movie> movies = movieService.queryRecommendMovieListByUserId(440);
        System.out.println(movies);
    }

}