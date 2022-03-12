package cn.edu.ecnu.repository;

import cn.edu.ecnu.model.dataobject.MovieDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void repositoryTest() {
        MovieDO movieDO = movieRepository.selectMovieById(1);
        System.out.println(movieDO);
    }
}
