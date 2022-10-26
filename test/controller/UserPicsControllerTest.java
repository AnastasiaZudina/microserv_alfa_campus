package ru.skillbox.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)

public class UserPicsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUserPics() throws Exception {

        String userId = "2";

        String request = String.format("{\"userId\":2, \"upic\":\"//sss/ss/ggfgf.gif\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post ("/userPics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Картинка пользователя с ID %s добавлена в базу", userId))));
    }

    @Test
    void getUserPics() throws Exception {

        String userId = "1";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get ("/userPics")
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    void updateUserPics() throws Exception {

        String userId = "1";

        String request = String.format("{\"userId\":1, \"upic\":\"//sss/ss/ggfg667f.gif\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put ("/userPics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Данные пользователя с ID %s сохранены", userId))));
    }

    @Test
    void deleteUserPics() throws Exception {

        String userId = "1";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/userPics")
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Картинка удалена", userId))));
    }



}
