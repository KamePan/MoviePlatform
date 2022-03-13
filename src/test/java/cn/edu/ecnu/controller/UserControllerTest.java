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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerTest() throws Exception {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("username", "bqh");
        jsonMap.put("password", "123456");
        jsonMap.put("gender", "1");
        jsonMap.put("email", "bqh@stu.edu.ecnu.cn");
//        jsonMap.put("birthday", Calendar.getInstance().getTime().toString());
        jsonMap.put("desc", "无敌");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(jsonMap);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/user/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonStr.getBytes(StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}
