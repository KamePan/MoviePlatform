package cn.edu.ecnu.convertor;

import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.model.entity.Rate;
import cn.edu.ecnu.model.response.RateQueryResponse;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RateConvertor {

    public static Rate convertResponseToEntity(RateQueryResponse rateQueryResponse) {
        Rate rate = new Rate();
        BeanUtils.copyProperties(rateQueryResponse, rate);
        return rate;
    }

    public static RateQueryResponse convertEntityToResponse(Rate rate) {
        RateQueryResponse rateQueryResponse = new RateQueryResponse();
        BeanUtils.copyProperties(rate, rateQueryResponse);
        return rateQueryResponse;
    }

    public static Rate convertDOToEntity(RateDO rateDO) {
        Rate rate = new Rate();
        BeanUtils.copyProperties(rateDO, rate);
        rate.setMovie(MovieConvertor.convertDOToEntity(rateDO.getMovieDO()));
        return rate;
    }

    public static RateDO convertEntityToDO(Rate rate) {
        RateDO rateDO = new RateDO();
        BeanUtils.copyProperties(rate, rateDO);
        rateDO.setMovieDO(MovieConvertor.convertEntityToDO(rate.getMovie()));
        return rateDO;
    }
}
