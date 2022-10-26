package ru.skillbox.demo.controller;

import org.junit.jupiter.api.AfterEach;
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

public class UserTextsControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void createUserTexts() throws Exception {

        String userId = "2";

        String request = String.format("{\"userId\":2, \"utext\":\"в чаще юга жил-был цитрус\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post ("/userTexts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Текст пользователя с ID %s добавлен в базу", userId))));
    }

    @Test
    void getUserTexts() throws Exception {

        String userId = "1";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get ("/userTexts")
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    void updateUserTexts() throws Exception {

        String userId = "1";

        String request = String.format("{\"userId\":1, \"utext\":\"да, но фальшивый экземпляр\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put ("/userTexts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Текст пользователя с ID %s сохранен", userId))));
    }

    @Test
    void deleteUserTexts() throws Exception {

        String userId = "1";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/userTexts")
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Текст пользователя успешно удален", userId))));
    }


}
