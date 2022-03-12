package cn.edu.ecnu.controller;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.response.MovieQueryResponse;
import cn.edu.ecnu.service.MovieService;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("电影信息 Controller")
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ApiOperation("根据电影 ID 返回电影详情")
    @GetMapping("/info/{id}")
    public Result<MovieQueryResponse> queryMovieInfoById(@PathVariable("id") Integer id) {
        Movie movie = movieService.queryMovieInfoByMovieId(id);
        MovieQueryResponse movieQueryResponse = MovieConvertor.convertEntityToResponse(movie);
        return ResultGenerator.genSuccessResult(movieQueryResponse);
    }

}
