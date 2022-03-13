package cn.edu.ecnu.util;

import cn.edu.ecnu.convertor.MovieConvertor;
import cn.edu.ecnu.model.dataobject.*;
import cn.edu.ecnu.model.entity.Movie;
import cn.edu.ecnu.model.entity.MovieActor;
import cn.edu.ecnu.model.entity.MovieDirector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

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

            /* 由于只有名称因此想导入关系也太难了 */
            List<DirectorDO> directorDOS = new ArrayList<>();
            for (Movie movie : movies) {
                /* 提取电影信息*/
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

    public static List<ActorDO> extractActorsFromFile() {
        BufferedReader reader = null;
        File file = new File("/Users/pankaiming/IdeaProjects/MovieRecommendSystem/src/main/resources/dataset/dbmovies.json");
        try {
            reader = new BufferedReader(new FileReader(file));
            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken< ArrayList<Movie>>(){}.getType();
            List<Movie> movies = gson.fromJson(reader, type);
            Set<String> actorSet = new HashSet<>();
            List<ActorDO> actorDOS = new ArrayList<>();

            /* 由于只有名称因此想导入关系也太难了 */
            for (Movie movie : movies) {
                List<ActorDO> actors = movie.getActor();
                if (actors == null) continue;
                for (ActorDO actorDO1: actors) {
                    String actor = actorDO1.getName();
                    actor = actor.replaceAll("\"", "");
                    if (!actorSet.contains(actor)) {
                        ActorDO actorDO = new ActorDO();
                        actorDO.setName(actor);
                        actorDO.setGender(0);
                        actorDO.setNationality("中国");
                        actorDO.setDesc("很好的演员...");
                        actorDOS.add(actorDO);

                        actorSet.add(actor);
                    }
                }
            }
            return actorDOS;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<DirectorDO> extractDirectorsFromFile() {
        BufferedReader reader = null;
        File file = new File("/Users/pankaiming/IdeaProjects/MovieRecommendSystem/src/main/resources/dataset/dbmovies.json");
        try {
            reader = new BufferedReader(new FileReader(file));
            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken< ArrayList<Movie>>(){}.getType();
            List<Movie> movies = gson.fromJson(reader, type);
            Set<String> directorSet = new HashSet<>();
            List<DirectorDO> directorDOS = new ArrayList<>();

            /* 由于只有名称因此想导入关系也太难了 */
            for (Movie movie : movies) {
                List<DirectorDO> directors = movie.getDirector();
                if (directors == null) continue;
                for (DirectorDO directorDO1: directors) {
                    String director = directorDO1.getName();
                    director = director.replaceAll("\"", "");
                    if (!directorSet.contains(director)) {
                        DirectorDO directorDO = new DirectorDO();
                        directorDO.setName(director);
                        directorDO.setGender(0);
                        directorDO.setNationality("中国");
                        directorDO.setDesc("很好的导演...");
                        directorDOS.add(directorDO);

                        directorSet.add(director);
                    }
                }
            }
            return directorDOS;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<MovieActor> extractMovieActorFromFile() {
        BufferedReader reader = null;
        File file = new File("/Users/pankaiming/IdeaProjects/MovieRecommendSystem/src/main/resources/dataset/out_actor_new.csv");
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String line = null;
            List<MovieActor> movieActors = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] act = line.split(",");
                MovieActor movieActor = new MovieActor();
                movieActor.setMovie(act[0]);
                movieActor.setActor(act[1]);
                movieActors.add(movieActor);
            }
            return movieActors;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<MovieDirector> extractMovieDirectorFromFile() {
        BufferedReader reader = null;
        File file = new File("/Users/pankaiming/IdeaProjects/MovieRecommendSystem/src/main/resources/dataset/out_director_new.csv");
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine(); // 去掉文件中的第一行标题
            String line = null;
            List<MovieDirector> movieDirectors = new ArrayList<>();

            while((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] act = line.split(",");
                MovieDirector movieDirector = new MovieDirector();
                movieDirector.setMovie(act[0]);
                movieDirector.setDirector(act[1]);
                movieDirectors.add(movieDirector);
            }
            return movieDirectors;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<UserDO> extractUsersFromFile() {
        BufferedReader reader = null;
        File file = new File("/Users/pankaiming/IdeaProjects/MovieRecommendSystem/src/main/resources/dataset/out_user_new.csv");
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String line = null;
            List<UserDO> userDOS = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                UserDO userDO = new UserDO();
                userDO.setId(Integer.parseInt(line));
                userDO.setUsername(line);
                userDO.setPassword("123");
                userDO.setDesc("一个普通用户...");
                userDO.setBirthday(Calendar.getInstance().getTime());
                userDO.setGender(0);
                userDOS.add(userDO);
            }
            return userDOS;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<MovieDirector> movieDirectors = extractMovieDirectorFromFile();
        List<MovieActor> movieActors = extractMovieActorFromFile();
        System.out.println(movieDirectors.size());
        System.out.println(movieActors.size());
    }
}
