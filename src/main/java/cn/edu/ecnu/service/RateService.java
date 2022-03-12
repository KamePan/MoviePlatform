package cn.edu.ecnu.service;

import cn.edu.ecnu.convertor.RateConvertor;
import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.model.entity.Rate;
import cn.edu.ecnu.model.dataobject.StatisticDO;
import cn.edu.ecnu.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public List<Rate> queryRateByUserId(Integer id) {
        List<RateDO> rateDOS = rateRepository.selectRateByUserId(id);
        List<Rate> rates = rateDOS.stream()
                .map(rateDO -> RateConvertor.convertDOToEntity(rateDO))
                .collect(Collectors.toList());
        return rates;
    }

    public List<Rate> queryRateByMovieId(Integer id) {
        List<RateDO> rateDOS = rateRepository.selectRateByMovieId(id);
        List<Rate> rates = rateDOS.stream()
                .map(rateDO -> RateConvertor.convertDOToEntity(rateDO))
                .collect(Collectors.toList());
        return rates;
    }

    public List<Rate> queryRateByUserAndMovieId(Integer userId, Integer movieId) {
        List<RateDO> rateDOS = rateRepository.selectRateByUserAndMovieId(userId, movieId);
        List<Rate> rates = rateDOS.stream()
                .map(rateDO -> RateConvertor.convertDOToEntity(rateDO))
                .collect(Collectors.toList());
        return rates;
    }

    public void insertUserMovieRate(Rate rate) {
        rateRepository.insertRate(RateConvertor.convertEntityToDO(rate));
    }

    public void updateUserMovieRate(Rate rate) {
        rateRepository.updateRate(RateConvertor.convertEntityToDO(rate));
    }

    public void deleteRateById(Integer id) {
        rateRepository.deleteRate(id);
    }

    public StatisticDO queryRateStatisticByUserId(Integer id) {
        StatisticDO statisticDO = rateRepository.selectStatisticByUserId(id);
        return statisticDO;
    }


}
