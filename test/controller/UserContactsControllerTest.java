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

public class UserContactsControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUserContacts() throws Exception {

        String userId = "2";
        String nickname = "Vasyan123";

        String request = String.format("{\"userId\":2, \"nickname\":\"Vasyan123\", \"phoneNum\":\"+79865523\", \"mailAddress\":\"Ivanovich@mail.love\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post ("/userContacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Данные пользователя с ID %s добавлены в базу", userId))));
    }

    @Test
    void getUserContacts() throws Exception {

        String userId = "1";
        boolean deleted = false;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get ("/userContacts")
                .param("deleted", String.valueOf(deleted))
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    void updateUserContacts() throws Exception {

        String userId = "2";
        String nickname = "Vasyan123";

        String request = String.format("{\"userId\":2, \"nickname\":\"Vasyan123\", \"phoneNum\":\"+7986552344\", \"mailAddress\":\"Ivanovich@mail.love\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put ("/userContacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("данные пользователя с ID %s сохранены", userId))));
    }

    @Test
    void deleteUserContacts() throws Exception {

        String userId = "2";
        boolean deleted = false;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/userContacts")
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Данные пользователя с ID %s успешно удалены", userId))));
    }



}
