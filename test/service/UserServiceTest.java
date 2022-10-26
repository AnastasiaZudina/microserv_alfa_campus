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
import ru.skillbox.demo.repository.UserRepository;

import javax.persistence.*;

import java.util.Optional;

import static org.mockito.Mockito.*;



@RunWith(SpringRunner.class)
@SpringBootTest()

class UserServiceTest {

    private Long userId;
    private User user;

    @Autowired
    @InjectMocks
    private UserService userService;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userId = 1L;
    }

    @DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

    @Test
    void createUserSuccess() {

        //given
        User user=  new User();
        User savedUser = new User(userId);
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);

        //when
        String result = userService.createUser(user);

        //then
        Assertions.assertEquals(String.format("Пользователь добавлен в базу с ID %s", userId), result);

    }

    @Test
    void getUserSuccess() {

        //given
        Mockito.when(userRepository.findByUserId(userId)).thenReturn(Optional.of(user));

        //when
        Long id = userService.getUser(userId).getUserId();

        //then
        Assertions.assertEquals(userId, id);
    }

    @Test
    void getUserError() {

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //given
        lenient().when(userRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userService.getUser(user.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }

    @Test
    void deleteUserSuccess(){

        //given
        Mockito.when(userRepository.findByUserId(userId)).thenReturn(Optional.of(user));

        //when
        String result = userService.deleteUser(user.getUserId());

        //then
        Assertions.assertEquals(String.format("Пользователь с ID %s успешно удален", userId), result);

    }


    @Test
    void createUserError() {

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //given

        Mockito.when(userRepository.save(user)).thenThrow(PersistenceException.class);

        //when
        Executable executable = () -> userService.createUser(user);

        //then
        Assertions.assertThrows(PersistenceException.class, executable);

    }

    @Test
    void deleteUserError() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //given
        Mockito.when(userRepository.findByUserId(1L)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userService.deleteUser(user.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }



}