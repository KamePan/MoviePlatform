package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.RateDO;
import cn.edu.ecnu.model.dataobject.StatDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class RateRepositoryTest {

    @Autowired
    private RateRepository rateRepository;

    @Test
    public void selectRateByUserIdTest() {
        List<RateDO> rateDOS = rateRepository.selectRateByUserId(153176);
        System.out.println(rateDOS);
    }

    @Test
    public void selectRateByMovieId() {
        List<RateDO> rateDOS = rateRepository.selectRateByMovieId(1);
        System.out.println(rateDOS);
    }

    @Test
    public void selectRateByUserAndMovieId() {
        List<RateDO> rateDOS = rateRepository.selectRateByUserAndMovieId(153176, 1);
        System.out.println(rateDOS);
    }

    @Test
    public void insertRateTest() {
        RateDO rateDO = new RateDO();
        rateDO.setUserId(153176);
        rateDO.setMovieId(1);
        rateDO.setRateTime(Calendar.getInstance().getTime());
        rateDO.setRate(3D);
        rateDO.setId(1000);
        rateDO.setComment("平凡而朴实的电影");
        rateRepository.insertRate(rateDO);
    }

    @Test
    public void updateRateTest() {
        RateDO rateDO = new RateDO();
        rateDO.setRate(5D);
        rateDO.setId(1000);
        rateDO.setRateTime(Calendar.getInstance().getTime());
        rateDO.setComment("看第二遍了，真不错");
        rateRepository.updateRate(rateDO);
    }

    @Test
    public void deleteRateTest() {
        rateRepository.deleteRate(1000);
    }

    @Test void selectStatisticByUserIdTest() {
        StatDO statDO = rateRepository.selectStatisticByUserId(153176);
        System.out.println(statDO);
    }
}
