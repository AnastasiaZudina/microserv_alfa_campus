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
public class UserSkillsControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUserSkills() throws Exception {

        String userId = "2";

        String request = String.format("{\"userId\":2, \"uskill\":\"drawing\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post ("/userSkills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Навык пользователя ID %s добавлен в базу", userId))));
    }

    @Test
    void getUserSkills() throws Exception {

        String userId = "1";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get ("/userSkills")
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].userId").value(userId));
    }

    /*@Test
    void updateUserSkills() throws Exception {

        String userId = "1";

        String request = String.format("{\"id\":10,\"userId\":1, \"uskill\":\"music\"}");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put ("/userSkills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Навык пользователя c ID %s сохранен", userId))));
    }*/

    @Test
    void deleteUserSkills() throws Exception {

        String userId = "2";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/userSkills")
                .param("userId", userId)
                .param("uskill", "drawing"));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Навык пользователя с ID %s успешно удален", userId))));
    }





}
