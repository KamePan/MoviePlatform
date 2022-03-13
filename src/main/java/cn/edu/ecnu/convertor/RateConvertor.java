package cn.edu.ecnu.convertor;

import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.model.dataobject.StatDO;
import cn.edu.ecnu.model.entity.Rate;
import cn.edu.ecnu.model.entity.Stat;
import cn.edu.ecnu.model.request.RateModifyRequest;
import cn.edu.ecnu.model.response.RateQueryResponse;
import cn.edu.ecnu.model.response.RateStatResponse;
import org.springframework.beans.BeanUtils;

import java.util.Calendar;

public class RateConvertor {

    public static Rate convertRequestToEntity(RateModifyRequest rateModifyRequest) {
        Rate rate = new Rate();
        BeanUtils.copyProperties(rateModifyRequest, rate);
        rate.setRateTime(Calendar.getInstance().getTime());
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
        return rate;
    }

    public static RateDO convertEntityToDO(Rate rate) {
        RateDO rateDO = new RateDO();
        BeanUtils.copyProperties(rate, rateDO);
        return rateDO;
    }

    public static Stat convertDOToEntity(StatDO statDO) {
        Stat stat = new Stat();
        BeanUtils.copyProperties(statDO, stat);
        return stat;
    }

    public static RateStatResponse convertEntityToResponse(Stat stat) {
        RateStatResponse rateStatResponse = new RateStatResponse();
        BeanUtils.copyProperties(stat, rateStatResponse);
        return rateStatResponse;
    }


}
