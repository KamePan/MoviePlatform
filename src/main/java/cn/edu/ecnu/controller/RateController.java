package cn.edu.ecnu.controller;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.convertor.RateConvertor;
import cn.edu.ecnu.model.entity.Rate;
import cn.edu.ecnu.model.request.RateInsertRequest;
import cn.edu.ecnu.model.response.RateQueryResponse;
import cn.edu.ecnu.model.response.RateStatisticResponse;
import cn.edu.ecnu.service.MovieService;
import cn.edu.ecnu.service.RateService;
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
    private MovieService movieService;

    @Autowired
    private RateService rateService;

    @ApiOperation("根据用户 ID 返回用户全部评分")
    @GetMapping("/user/{id}")
    public Result<List<RateQueryResponse>> queryRateByUserId(@PathVariable Integer id) {
        List<Rate> rates = rateService.queryRateByUserId(id);
        List<RateQueryResponse> rateQueryResponses = rates.stream()
                .map(rate -> RateConvertor.convertEntityToResponse(rate))
                .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(rateQueryResponses);
    }

    @ApiOperation("根据电影 ID 返回电影全部评分")
    @GetMapping("/movie/{id}")
    public Result<List<RateQueryResponse>> queryRateByMovieId(@PathVariable Integer id) {
        List<Rate> rates = rateService.queryRateByMovieId(id);
        List<RateQueryResponse> rateQueryResponses = rates.stream()
                .map(rate -> RateConvertor.convertEntityToResponse(rate))
                .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(rateQueryResponses);
    }

    @ApiOperation("根据电影 ID 及用户 ID 返回当前用户对当前电影评分")
    @GetMapping("/movie/user")
    public Result<List<RateQueryResponse>> queryRateByUserAndMovieId(@RequestParam("user_id") Integer userId,
                                                               @RequestParam("movie_id") Integer movieId) {
        List<Rate> rates = rateService.queryRateByUserAndMovieId(userId, movieId);
        List<RateQueryResponse> rateQueryResponses = rates.stream()
                .map(rate -> RateConvertor.convertEntityToResponse(rate))
                .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(rateQueryResponses);
    }

    @ApiOperation("添加用户对电影评分数据")
    @PostMapping("/insert")
    public Result<Object> insertUserMovieRate(RateInsertRequest rateInsertRequest) {

        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据评分 ID 删除电影评分")
    @DeleteMapping("/delete/{id}")
    public Result<Object> deleteRateById(@PathVariable Integer id) {

        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据评分 ID 更新电影评分")
    @PutMapping("/update/{id}")
    public Result<Object> updateRateById(@PathVariable Integer id) {

        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据用户 ID 查询当前用户电影评分统计数据")
    @GetMapping("/stat/{id}")
    public Result<RateStatisticResponse> queryRateStatisticByUserId(@PathVariable Integer id) {

        return ResultGenerator.genSuccessResult();
    }

}
