package cn.edu.ecnu.controller;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.convertor.RateConvertor;
import cn.edu.ecnu.convertor.UserConvertor;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.entity.Rate;
import cn.edu.ecnu.model.entity.Stat;
import cn.edu.ecnu.model.entity.User;
import cn.edu.ecnu.model.request.RateModifyRequest;
import cn.edu.ecnu.model.response.RateQueryResponse;
import cn.edu.ecnu.model.response.RateStatResponse;
import cn.edu.ecnu.model.response.UserQueryResponse;
import cn.edu.ecnu.service.MovieService;
import cn.edu.ecnu.service.RateService;
import cn.edu.ecnu.service.UserService;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @ApiOperation("根据用户 ID 返回用户全部评分")
    @GetMapping("/user/rate/{id}")
    public Result<List<RateQueryResponse>> queryRateByUserIdOrderByRate(@PathVariable Integer id) {
        List<Rate> rates = rateService.queryRateByUserIdOrderByRate(id);
        List<RateQueryResponse> rateQueryResponses = rates.stream()
                .map(rate -> {
                    Integer movieId = rate.getMovieId();
                    RateQueryResponse rateQueryResponse = RateConvertor.convertEntityToResponse(rate);
                    Movie movie = movieService.queryMovieInfoByMovieId(movieId);
                    rateQueryResponse.setMovie(MovieConvertor.convertEntityToResponse(movie));
                    return rateQueryResponse;
                }).collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(rateQueryResponses);
    }

    @ApiOperation("根据用户 ID 返回用户全部评分")
    @GetMapping("/user/time/{id}")
    public Result<List<RateQueryResponse>> queryRateByUserIdOrderByTime(@PathVariable Integer id) {
        List<Rate> rates = rateService.queryRateByUserIdOrderByTime(id);
        List<RateQueryResponse> rateQueryResponses = rates.stream()
                .map(rate -> {
                    Integer movieId = rate.getMovieId();
                    RateQueryResponse rateQueryResponse = RateConvertor.convertEntityToResponse(rate);
                    Movie movie = movieService.queryMovieInfoByMovieId(movieId);
                    rateQueryResponse.setMovie(MovieConvertor.convertEntityToResponse(movie));
                    return rateQueryResponse;
                }).collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(rateQueryResponses);
    }

    @ApiOperation("根据电影 ID 返回电影全部评分")
    @GetMapping("/movie/{id}")
    public Result<List<RateQueryResponse>> queryRateByMovieId(@PathVariable Integer id) {
        List<Rate> rates = rateService.queryRateByMovieId(id);
        List<RateQueryResponse> rateQueryResponses = rates.stream()
                .map(rate -> {
                    RateQueryResponse rateQueryResponse = RateConvertor.convertEntityToResponse(rate);
                    User user = userService.queryUserById(rate.getUserId());
                    rateQueryResponse.setUser(UserConvertor.convertEntityToResponse(user));
                    return rateQueryResponse;
                })
                .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(rateQueryResponses);
    }

    @ApiOperation("根据电影 ID 及用户 ID 返回当前用户对当前电影评分")
    @GetMapping("/movie_user")
    public Result<RateQueryResponse> queryRateByUserAndMovieId(@RequestParam("userId") Integer userId,
                                                               @RequestParam("movieId") Integer movieId) {
        Rate rate = rateService.queryRateByUserAndMovieId(userId, movieId);
        RateQueryResponse rateQueryResponse = RateConvertor.convertEntityToResponse(rate);
        return ResultGenerator.genSuccessResult(rateQueryResponse);
    }

    @ApiOperation("添加用户对电影评分数据")
    @PostMapping("/insert")
    public Result insertUserMovieRate(@RequestBody RateModifyRequest rateModifyRequest) {
        Rate rate = RateConvertor.convertRequestToEntity(rateModifyRequest);
        rateService.insertUserMovieRate(rate);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据评分 ID 删除电影评分")
    @PostMapping("/delete/{id}")
    public Result deleteRateById(@PathVariable Integer id) {
        rateService.deleteRateById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据评分 ID 更新电影评分")
    @PostMapping("/update")
    public Result updateRateById(@RequestBody RateModifyRequest rateModifyRequest) {
        Rate rate = RateConvertor.convertRequestToEntity(rateModifyRequest);
        rateService.updateUserMovieRate(rate);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据用户 ID 查询当前用户电影评分统计数据")
    @GetMapping("/stat/{id}")
    public Result<RateStatResponse> queryRateStatisticByUserId(@PathVariable Integer id) {
        Stat stat = rateService.queryRateStatisticByUserId(id);
        RateStatResponse rateStatResponse = RateConvertor.convertEntityToResponse(stat);
        return ResultGenerator.genSuccessResult(rateStatResponse);
    }

}
