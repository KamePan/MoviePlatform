package cn.edu.ecnu.controller;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.request.MovieRequest;
import cn.edu.ecnu.model.response.MovieResponse;
import cn.edu.ecnu.service.MovieService;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/info/{id}")
    public Result<MovieResponse> queryMovieInfoById(@PathVariable("id") Integer id) {
        Movie movie = movieService.queryMovieInfoByMovieId(id);
        MovieResponse movieResponse = MovieConvertor.convertEntityToResponse(movie);
        return ResultGenerator.genSuccessResult(movieResponse);
    }


}
