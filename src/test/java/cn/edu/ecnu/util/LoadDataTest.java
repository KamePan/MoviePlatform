package cn.edu.ecnu.util;

import cn.edu.ecnu.model.dataobject.*;
import cn.edu.ecnu.model.entity.MovieActor;
import cn.edu.ecnu.model.entity.MovieDirector;
import cn.edu.ecnu.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class LoadDataTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectRepository directRepository;

    @Autowired
    private ActRepository actRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void loadDataTest() {
        System.out.println("开始导入...");
        List<MovieDO> movieDOS = DataExtractor.extractMoviesFromFile();
        List<ActorDO> actorDOS = DataExtractor.extractActorsFromFile();
        List<DirectorDO> directorDOS = DataExtractor.extractDirectorsFromFile();
//        movieRepository.insertBatchMovies(movieDOS);
        directorRepository.insertBatchDirectors(directorDOS);
        actorRepository.insertBatchActors(actorDOS);
        System.out.println("导入成功...");
    }

    @Test
    public void selectMovieByTitleTest() {
        List<MovieDO> movieDOS = movieRepository.selectMovieByTitle("小逃亡者");
        System.out.println(movieDOS);
    }

    @Test
    public void findActorIdTest() {
        long start = Calendar.getInstance().getTimeInMillis();
        List<ActorDO> actorDOS = actorRepository.selectActorByName("万国鹏");
        List<MovieDO> movieDOS = movieRepository.selectMovieByTitle("我是路人甲");
        List<DirectorDO> directorDOS = directorRepository.selectDirectorByName("尔冬升");
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println(movieDOS);
        System.out.println(actorDOS);
        System.out.println(directorDOS);
        System.out.println(end - start + "ms");

        Map<String, Integer> movieTitleIdMap = new HashMap<>();
        List<ActDO> actDOS = new ArrayList<>();
        List<DirectDO> directDOS = new ArrayList<>();

        System.out.println("build movie id map");
        List<MovieDO> movieDOSList = movieRepository.selectAllMovies();
        for (MovieDO movieDO : movieDOSList) {
            movieTitleIdMap.put(movieDO.getTitle(), movieDO.getId());
        }

        System.out.println("query all director ids");
        start = Calendar.getInstance().getTimeInMillis();
        List<MovieDirector> movieDirectors = DataExtractor.extractMovieDirectorFromFile();
        for (MovieDirector movieDirector : movieDirectors) {
            List<DirectorDO> directorDOList = directorRepository.selectDirectorByName(movieDirector.getDirector());
            Integer movieId = movieTitleIdMap.get(movieDirector.getMovie());
            DirectDO directDO = new DirectDO();
            directDO.setDirectorId(directorDOList.get(0).getId());
            directDO.setMovieId(movieId);
            directDOS.add(directDO);
        }
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(end - start + "ms");

        System.out.println("query all actor ids");
        start = Calendar.getInstance().getTimeInMillis();
        List<MovieActor> movieActors = DataExtractor.extractMovieActorFromFile();
        for (MovieActor movieActor : movieActors) {
            List<ActorDO> actorDOList = actorRepository.selectActorByName(movieActor.getActor());
            Integer movieId = movieTitleIdMap.get(movieActor.getMovie());
            ActDO actDO = new ActDO();
            actDO.setActorId(actorDOList.get(0).getId());
            actDO.setMovieId(movieId);
            actDOS.add(actDO);
        }
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(end - start + "ms");

        System.out.println("insert act and direct");
        start = Calendar.getInstance().getTimeInMillis();
        actRepository.insertBatchAct(actDOS);
        directRepository.insertBatchDirect(directDOS);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println(end - start + "ms");

    }


    @Test
    public void loadUserData() {
        List<UserDO> userDOS = DataExtractor.extractUsersFromFile();
        userRepository.insertBatchUsers(userDOS);
    }
}
