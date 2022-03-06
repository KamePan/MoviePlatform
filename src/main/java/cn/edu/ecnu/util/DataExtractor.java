package cn.edu.ecnu.util;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.model.dataobject.MovieDO;
import cn.edu.ecnu.model.entity.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

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
                MovieDO movieDO = MovieConvertor.convertEntityToDO(movie);
                movieDO.setTitle(movie.getTitle().replaceAll("\"", ""));
                /* 提取封面中的 cover_id */
                if (StringUtils.isBlank(movie.getCover())) {
                    continue;
                }
                String cover = movie.getCover();
                int slash_idx = cover.lastIndexOf('/');
                int point_idx = cover.lastIndexOf('.');
                String cover_id = cover.substring(slash_idx + 2, point_idx);
                movieDO.setCover(cover_id);
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
