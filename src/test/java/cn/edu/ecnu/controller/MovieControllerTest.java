package cn.edu.ecnu.controller;

import com.google.gson.Gson;
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
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void queryMovieById() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get("/movie/info/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void queryMovieRecommendListByUserId() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/movie/recommend/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void queryMovieByCondition() throws Exception {
        Map<String, String> jsonMap = new HashMap<>();
        //jsonMap.put("title", "路人甲");
        jsonMap.put("language", "汉语");
        jsonMap.put("district", "中国");
        jsonMap.put("category", "科幻");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(jsonMap);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/movie/search")
                .content(jsonStr.getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}
