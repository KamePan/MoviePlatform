package cn.edu.ecnu.service;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie queryMovieInfoByMovieId(Integer id) {
        MovieDO movieDO = movieRepository.selectMovieById(id);
        Movie movie = MovieConvertor.convertDOToEntity(movieDO);
        return movie;
    }
}
