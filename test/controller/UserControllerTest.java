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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUser() throws Exception {

        String request = "{\"id\": \"deleted\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post ("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

            resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo("Пользователь добавлен в базу с ID 10")));
    }

    @Test
    void deleteUser() throws Exception {

        Long userId = 1l;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete ("/users/{request}", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Пользователь с ID %s успешно удален", userId))));
    }


    @Test
    void getUser() throws Exception {

        Long userId = 1l;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get ("/users/{request}", userId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    public void getUserNotFound() throws Exception {

        Long exceptionParam = 999l;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{request}", exceptionParam))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUserBadRequest() throws Exception {

        String exceptionParam = "bad";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{request}", exceptionParam))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteUserNotFound() throws Exception {

        Long exceptionParam = 999l;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{request}", exceptionParam))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserBadRequest() throws Exception {

        String exceptionParam = "bad";

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{request}", exceptionParam))
                .andExpect(status().isBadRequest());
    }



}