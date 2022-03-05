package cn.edu.ecnu.convertor;

import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.response.MovieResponse;
import org.springframework.beans.BeanUtils;

public class MovieConvertor {

    public static Movie convertResponseToEntity(MovieResponse movieResponse) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieResponse, movie);
        return movie;
    }

    public static MovieResponse convertEntityToResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        BeanUtils.copyProperties(movie, movieResponse);
        return movieResponse;
    }

    public static Movie convertDOtoEntity(MovieDO movieDO) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDO, movie);
        return movie;
    }
}
