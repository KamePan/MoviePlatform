package cn.edu.ecnu.convertor;

import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.response.MovieQueryResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MovieConvertor {

    public static Movie convertResponseToEntity(MovieQueryResponse movieQueryResponse) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieQueryResponse, movie);
        return movie;
    }

    public static MovieQueryResponse convertEntityToResponse(Movie movie) {
        MovieQueryResponse movieQueryResponse = new MovieQueryResponse();
        BeanUtils.copyProperties(movie, movieQueryResponse);
        return movieQueryResponse;
    }

    public static Movie convertDOToEntity(MovieDO movieDO) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDO, movie);
        if (movieDO.getCategory() != null)
            movie.setCategory(Arrays.stream(StringUtils.split(movieDO.getCategory() , ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getComposer() != null)
            movie.setComposer(Arrays.stream(StringUtils.split(movieDO.getComposer(), ", "))
                    .collect(Collectors.toList()));
        if (movieDO.getDistrict() != null)
            movie.setDistrict(Arrays.stream(StringUtils.split(movieDO.getDistrict(), ", "))
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
        if (movie.getCategory() != null)
            movieDO.setCategory(String.join(", ", movie.getCategory()));
        if (movie.getComposer() != null)
            movieDO.setComposer(String.join(", ", movie.getComposer()));
        if (movie.getDistrict() != null)
            movieDO.setDistrict(String.join(", ", movie.getDistrict()));
        if (movie.getLanguage() != null)
            movieDO.setLanguage(String.join(", ", movie.getLanguage()));
        if (movie.getOthername() != null)
            movieDO.setOthername(String.join(", ", movie.getOthername()));
        return movieDO;
    }
}
