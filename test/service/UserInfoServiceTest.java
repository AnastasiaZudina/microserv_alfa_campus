package ru.skillbox.demo.service;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.entity.UserInfo;
import ru.skillbox.demo.repository.UserInfoRepository;
import ru.skillbox.demo.repository.UserRepository;

import javax.persistence.*;

import java.util.Optional;

import static org.mockito.Mockito.*;



@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserInfoServiceTest {

    private Long userId;
    private User user;
    private UserInfo userInfo;

    @Autowired
    @InjectMocks
    private UserInfoService userInfoService;

    @Mock
    private UserInfoRepository userInfoRepository;


    @BeforeEach
    void setUp(){
        userId = 1L;
        user = new User(userId);
        userInfo = new UserInfo(userId,"Vasili", "Vasiliev","Kuzmich","M","01/01/2000","Moscow");

    }

    @DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)


    @Test
    void createUserInfoSuccess() {

        userId = 2L;

        //given
        UserInfo userInfo=  new UserInfo(userId,"Vasili", "Vasiliev","","M","01/01/2000","Moscow");
        UserInfo savedUserInfo = new UserInfo(userId,"Vasili", "Vasiliev","","M","01/01/2000","Moscow");
        Mockito.when(userInfoRepository.save(userInfo)).thenReturn(savedUserInfo);

        //when
        String result = userInfoService.createUserInfo(userInfo);

        //then
        Assertions.assertEquals(String.format("Данные пользователя %s с ID %s добавлены в базу", userInfo.getFam(), userInfo.getUserId()), result);

    }

    @Test
    void getUserInfoSuccess() {

        Long userId = 1L;

        //given
        Mockito.when(userInfoRepository.findByUserId(userId)).thenReturn(Optional.of(userInfo));

        //when
        Long id = userInfoService.getUserInfo(userId, false).getUserId();

        //then
        Assertions.assertEquals(userId, id);

    }

    @Test
    void updateUserInfoSuccess(){

        UserInfo userInfo = new UserInfo(userId,"Vasili", "Vasiliev","Kuzmich","M","01/01/2000","Moscow");

        //given
        Mockito.when(userInfoRepository.findByUserId(userId)).thenReturn(Optional.of(userInfo));

        //when
        String result = userInfoService.updateUserInfo(userInfo);

        //then
        Assertions.assertEquals(String.format("данные пользователя %s с ID %s сохранены", userInfo.getFam(), userInfo.getUserId()), result);

    }

    @Test
    void deleteUserInfoSuccess(){

         //given
        Mockito.when(userInfoRepository.findByUserId(userId)).thenReturn(Optional.of(userInfo));

        //when
        String result = userInfoService.deleteUserInfo(userId, false);

        //then
        Assertions.assertEquals(String.format("Данные пользователя с ID %s успешно удалены", userInfo.getUserId()), result);

    }

    @Test
    void createUserInfoError(){

        UserInfo userInfo = new UserInfo(999L,"Vasili", "Vasiliev","Kuzmich","M","01/01/2000","Moscow");

        //given
        Mockito.when(userInfoRepository.save(userInfo)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userInfoService.createUserInfo(userInfo);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);

    }

    @Test
    void deleteUserInfoError() {
       UserInfoRepository userInfoRepository = Mockito.mock(UserInfoRepository.class);
       UserInfoService userInfoService = new UserInfoService(userInfoRepository);

        //given
        Mockito.when(userInfoRepository.findByUserId(userId)).thenThrow(PersistenceException.class);

        //when
        Executable executable = () -> userInfoService.deleteUserInfo(userInfo.getUserId(), false);

        //then
        Assertions.assertThrows(PersistenceException.class, executable);
    }

    @Test
    void getUserInfoError() {

        UserInfoRepository userInfoRepository = Mockito.mock(UserInfoRepository.class);
        UserInfoService userInfoService = new UserInfoService(userInfoRepository);

        //given
        lenient().when(userInfoRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userInfoService.getUserInfo(userInfo.getUserId(), false);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }

    @Test
    void updateUserInfoError() {

        UserInfo userInfo = new UserInfo(userId,"Vasili", "Vasiliev","Kuzmich","M","01/01/2000","Moscow");

        UserInfoRepository userInfoRepository = Mockito.mock(UserInfoRepository.class);
        UserInfoService userInfoService = new UserInfoService(userInfoRepository);

        //given
        lenient().when(userInfoRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userInfoService.updateUserInfo(userInfo);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }


}
