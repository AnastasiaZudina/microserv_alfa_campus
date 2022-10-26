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
import ru.skillbox.demo.entity.UserPics;
import ru.skillbox.demo.repository.UserInfoRepository;
import ru.skillbox.demo.repository.UserPicsRepository;
import ru.skillbox.demo.repository.UserRepository;

import javax.persistence.PersistenceException;
import java.util.Optional;

import static org.mockito.Mockito.lenient;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserPicsServiceTest {
    private Long userId;
    private UserPics userPics;
    private User user;

    @Autowired
    @InjectMocks
    private UserPicsService userPicsService;

    @Mock
    private UserPicsRepository userPicsRepository;

    @Mock
    private UserRepository userRepository;

    @DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

    @BeforeEach
    void setUp(){
        userId = 2L;
        user = new User(userId);
        userPics = new UserPics(userId, "dfidjfkdj.png");
    }

    @Test
    void createUserPicsSuccess() {

        //given
        UserPics userPics =  new UserPics(userId, "dfidjfkdj.png");
        UserPics savedUserPics = new UserPics(userId, "dfidjfkdj.png");
        Mockito.when(userPicsRepository.save(userPics)).thenReturn(savedUserPics);

        //when
        String result = userPicsService.createUserPics(userPics);

        //then
        Assertions.assertEquals(String.format("Картинка пользователя с ID %s добавлена в базу", userId), result);

    }

    @Test
    void updateUserPicsSuccess() {

        //given
        UserPics userPics =  new UserPics(userId, "dfidjfkdjgfgf.png");
        Mockito.when(userPicsRepository.findByUserId(userId)).thenReturn(Optional.of(userPics));

        //when
        String result = userPicsService.updateUserPics(userPics);

        //then
        Assertions.assertEquals(String.format("Картинка пользователя с ID %s добавлена в базу", userId), result);

    }

    @Test
    void getUserPicsSuccess() {

        userId = 1L;

        //given
        Mockito.when(userPicsRepository.findByUserId(userId)).thenReturn(Optional.of(userPics));

        //when
        Long id = userPicsService.getUserPics(userId).getUserId();

        //then
        Assertions.assertEquals(userId, id);
    }


    @Test
    void deleteUserPicsSuccess(){

        userId = 1L;
        userPics = new UserPics(userId, "dfidjfkdj.png");

        //given
        Mockito.when(userPicsRepository.findByUserId(userId)).thenReturn(Optional.of(userPics));

        //when
        String result = userPicsService.deleteUserPics(userPics.getUserId());

        //then
        Assertions.assertEquals("Картинка удалена", result);

    }

    @Test
    void createUserPicsError(){

        userId = 999L;
        userPics = new UserPics(userId, "dfidjfkdj.png");

        //given
        Mockito.when(userPicsRepository.save(userPics)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userPicsService.createUserPics(userPics);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);

    }

    @Test
    void deleteUserPicsError() {
        UserPicsRepository userPicsRepository = Mockito.mock(UserPicsRepository.class);
        UserPicsService userPicsService = new UserPicsService(userPicsRepository);

        //given
        Mockito.when(userPicsRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userPicsService.deleteUserPics(userPics.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void getUserPicsError() {

        UserPicsRepository userPicsRepository = Mockito.mock(UserPicsRepository.class);
        UserPicsService userPicsService = new UserPicsService(userPicsRepository);

        //given
        lenient().when(userPicsRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userPicsService.getUserPics(userPics.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }

    @Test
    void updateUserPicsError() {

        UserPics userPics = new UserPics(userId,"Vasili");

        UserPicsRepository userPicsRepository = Mockito.mock(UserPicsRepository.class);
        UserPicsService userPicsService = new UserPicsService(userPicsRepository);

        //given
        lenient().when(userPicsRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userPicsService.updateUserPics(userPics);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }




}
