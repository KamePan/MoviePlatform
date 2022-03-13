package cn.edu.ecnu.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
public class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void queryRateByUserIdTest() throws Exception {
        //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rate/user/153176")
//                        .content(json.getBytes()) //传json参数
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                // .header("Authorization","Bearer ********-****-****-****-************")
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void queryRateByMovieIdTest() throws Exception {
        //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rate/movie/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void queryRateByUserAndMovieId() throws Exception {
        //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/rate/movie_user")
                .param("user_id", "153176")
                .param("movie_id", "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void insertUserMovieRate() throws Exception {
        //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("id", "1000");
        jsonMap.put("user_id", "153176");
        jsonMap.put("movie_id", "1");
        jsonMap.put("rate", "2");
        jsonMap.put("comment", "电影不太好看");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(jsonMap);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/rate/insert")
                .content(jsonStr.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void updateUserMovieRate() throws Exception {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("id", "1000");
        jsonMap.put("rate", "4");
        jsonMap.put("comment", "重新看了一遍感觉还不错");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(jsonMap);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/rate/update")
                .content(jsonStr.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void deleteUserMovieRate() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/rate/delete/1000")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void queryRateStatisticByUserId() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/rate/stat/153176")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }


}
