package cn.edu.ecnu.util;

import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataExtractor {

    public static List<MovieDO> extractMoviesFromFile() {
        BufferedReader reader = null;
        File file = new File("/Users/pankaiming/IdeaProjects/MovieRecommendSystem/src/main/resources/dataset/dbmovies.json");
        try {
            reader = new BufferedReader(new FileReader(file));
            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken< ArrayList<Movie>>(){}.getType();
            List<Movie> movies = gson.fromJson(reader, type);
            List<MovieDO> movieDOS = new ArrayList<>();
            for (Movie movie : movies) {
                MovieDO movieDO = new MovieDO();

                /* 提取封面中的 cover_id */
                if (StringUtils.isBlank(movie.getCover())) {
                    continue;
                }
                String cover = movie.getCover();
                int slash_idx = cover.lastIndexOf('/');
                int point_idx = cover.lastIndexOf('.');
                String cover_id = cover.substring(slash_idx + 2, point_idx);
                movieDO.setCover(cover_id);
                movieDO.setTitle(movie.getTitle());
                movieDO.setLength(movie.getLength());
                movieDO.setRate(movie.getRate());
                movieDO.setShowtime(movie.getShowtime());
                movieDO.setUrl(movie.getUrl());
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
                movieDOS.add(movieDO);
            }
            return movieDOS;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        extractMoviesFromFile();
    }
}
