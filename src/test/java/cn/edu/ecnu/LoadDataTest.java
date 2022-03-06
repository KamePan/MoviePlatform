package cn.edu.ecnu;

import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.dto.MovieDTO;
import cn.edu.ecnu.repository.MovieDTORepository;
import cn.edu.ecnu.repository.MovieRepository;
import cn.edu.ecnu.repository.RateRepository;
import cn.edu.ecnu.util.DataExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LoadDataTest {

    @Autowired
    private MovieRepository movieRepository;

//    @Autowired
//    private RateRepository rateRepository;

    @Autowired
    private MovieDTORepository movieDTORepository;

    @Test
    public void loadDataTest() {
        System.out.println("开始导入...");
        List<MovieDO> movieDOS = DataExtractor.extractMoviesFromFile();
        movieRepository.insertBatchMovies(movieDOS);
        System.out.println("导入成功...");
    }

    @Test
    public void selectMovieByTitleTest() {
        List<MovieDO> movieDOS = movieRepository.selectMovieByTitle("小逃亡者");
        System.out.println(movieDOS);
    }

    @Test
    public void neo4jQueryTest() {
        List<MovieDTO> movieDTOS = movieDTORepository.queryMovieDTOByTitle("漩涡");
        System.out.println(movieDTOS);
    }



}
