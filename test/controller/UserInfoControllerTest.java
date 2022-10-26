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
public class UserInfoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUserInfo() throws Exception {

        String userId = "3";
        String fam = "Kuznetsov";

        String request = String.format("{\"userId\":3, \"name\":\"Vasya\", \"fam\":\"Kuznetsov\", \"otch\":\"Ivanovich\", \"gender\":\"M\", \"birthDate\":\"06.06.2006\", \"city\":\"Novgorod\"}", fam);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post ("/userInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Данные пользователя %s с ID %s добавлены в базу", fam, userId))));
    }

    @Test
    void getUserInfo() throws Exception {

        String userId = "1";
        boolean deleted = false;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get ("/userInfo")
                .param("deleted", String.valueOf(deleted))
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    void updateUserInfo() throws Exception {

        String userId = "2";
        String fam = "Kuznetsov";

        String request = String.format("{\"userId\":2, \"name\":\"Katya\", \"fam\":\"Kuznetsov\", \"otch\":\"Ivanovich\", \"gender\":\"M\", \"birthDate\":\"06.06.2006\", \"city\":\"Novgorod\"}", fam);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put ("/userInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("данные пользователя %s с ID %s сохранены", fam, userId))));
    }

    @Test
    void deleteUserInfo() throws Exception {

        String userId = "1";
        boolean deleted = false;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/userInfo")
                .param("userId", userId));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$",
                        equalTo(String.format("Данные пользователя с ID %s успешно удалены", userId))));
    }

}
