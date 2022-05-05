package cn.edu.ecnu;

import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class MovieRecommendSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRecommendSystemApplication.class, args);
    }

}
