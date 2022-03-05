package cn.edu.ecnu.convertor;

import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.response.MovieResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public static Movie convertDOToEntity(MovieDO movieDO) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDO, movie);
        if (movieDO.getActor() != null)
            movie.setActor(Arrays.stream(StringUtils.split(movieDO.getActor(), ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getCategory() != null)
            movie.setCategory(Arrays.stream(StringUtils.split(movieDO.getCategory() , ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getComposer() != null)
            movie.setComposer(Arrays.stream(StringUtils.split(movieDO.getComposer(), ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getDistrict() != null)
            movie.setDistrict(Arrays.stream(StringUtils.split(movieDO.getDistrict(), ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getDirector() != null)
            movie.setDirector(Arrays.stream(StringUtils.split(movieDO.getDirector(), ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getLanguage() != null)
            movie.setLanguage(Arrays.stream(StringUtils.split(movieDO.getLanguage(), ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getOthername() != null)
            movie.setOthername(Arrays.stream(StringUtils.split(movieDO.getOthername(), ", "))
                    .collect(Collectors.toList()));
        return movie;
    }

    public static MovieDO convertEntityToDO(Movie movie) {
        MovieDO movieDO = new MovieDO();
        BeanUtils.copyProperties(movie, movieDO);
        if (movie.getActor() != null)
            movieDO.setActor(String.join(", ", movie.getActor()));
        if (movie.getCategory() != null)
            movieDO.setCategory(String.join(", ", movie.getCategory()));
        if (movie.getComposer() != null)
            movieDO.setComposer(String.join(", ", movie.getComposer()));
        if (movie.getDistrict() != null)
            movieDO.setDistrict(String.join(", ", movie.getDistrict()));
        if (movie.getDirector() != null)
            movieDO.setDirector(String.join(", ", movie.getDirector()));
        if (movie.getLanguage() != null)
            movieDO.setLanguage(String.join(", ", movie.getLanguage()));
        if (movie.getOthername() != null)
            movieDO.setOthername(String.join(", ", movie.getOthername()));
        return movieDO;
    }
}
