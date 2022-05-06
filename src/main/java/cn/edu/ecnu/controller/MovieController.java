package cn.edu.ecnu.controller;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.exception.CustomizeException;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.enums.ResultCodeEnum;
import cn.edu.ecnu.model.request.MovieSearchRequest;
import cn.edu.ecnu.model.response.MovieQueryResponse;
import cn.edu.ecnu.service.MovieService;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @ApiOperation("根据用户 ID 返回电影推荐列表")
    @GetMapping("recommend/{id}")
    public Result queryMovieRecommendListByUserId(@PathVariable Integer id) {
        List<Movie> movies = movieService.queryRecommendMovieListByUserId(id);
        List<MovieQueryResponse> movieQueryResponses = movies.stream()
                .map(MovieConvertor::convertEntityToResponse)
                .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(movieQueryResponses);
    }

    @ApiOperation("获取高分电影")
    @GetMapping("/high")
    public Result queryHighScoreMovieList(@RequestParam Integer size) {
        List<Movie> movies = movieService.queryHighScoreMovieList(size);
        List<MovieQueryResponse> movieQueryResponses = movies.stream()
                .map(MovieConvertor::convertEntityToResponse)
                .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(movieQueryResponses);
    }

    @ApiOperation("根据条件分页搜索电影")
    @GetMapping("/search")
    public Result pageQueryMovieSelective(MovieSearchRequest movieSearchRequest) {
        if (Objects.isNull(movieSearchRequest) || Objects.isNull(movieSearchRequest.getPageNumber()) || Objects.isNull(movieSearchRequest.getPageSize())) {
            throw new CustomizeException(ResultCodeEnum.PARAM_ILLEGAL);
        }
        List<Movie> movies = movieService.pageQueryMovieSelective(movieSearchRequest);
        List<MovieQueryResponse> movieQueryResponses = movies.stream()
                .map(MovieConvertor::convertEntityToResponse)
                .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(movieQueryResponses);
    }

}
