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
import ru.skillbox.demo.entity.UserContacts;
import ru.skillbox.demo.repository.UserContactsRepository;

import javax.persistence.PersistenceException;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class userContactsServiceTest {

    private Long userId;
    private User user;
    private UserContacts userContacts;

    @Autowired
    @InjectMocks
    private UserContactsService userContactsService;

    @Mock
    private UserContactsRepository userContactsRepository;

    @DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

    @BeforeEach
    void setUp(){
        userId = 1L;
        user = new User(userId);
        userContacts = new UserContacts(1L, "Vasyan111", "+79999999","vasya111@mail.org");
    }


    @Test
    void createUserContactsSuccess() {

        //given
        UserContacts userContacts=  new UserContacts(2L, "Vasyan111", "+79999999","vasya111@mail.org");
        UserContacts savedUserContacts = new UserContacts(2L, "Vasyan111", "+79999999","vasya111@mail.org");
        Mockito.when(userContactsRepository.save(userContacts)).thenReturn(savedUserContacts);

        //when
        String result = userContactsService.createUserContacts(userContacts);

        //then
        Assertions.assertEquals(String.format("Данные пользователя с ID %s добавлены в базу",  userContacts.getUserId()), result);

    }

    @Test
    void getUserContactsSuccess() {

        Long userId = 1L;

        //given
        Mockito.when(userContactsRepository.findByUserIdAndDeleted(userId, false)).thenReturn(Optional.of(userContacts));

        //when
        Long id = userContactsService.getUserContacts(userId, false).getUserId();

        //then
        Assertions.assertEquals(userId, id);

    }

    @Test
    void updateUserContactsSuccess(){

        UserContacts userContacts = new UserContacts(userId, "Vasyan221", "+79999999","vasya111@mail.org");

        //given
        Mockito.when(userContactsRepository.findByUserIdAndDeleted(userId, false)).thenReturn(Optional.of(userContacts));

        //when
        String result = userContactsService.updateUserContacts(userContacts);

        //then
        Assertions.assertEquals(String.format("данные пользователя с ID %s сохранены", userContacts.getUserId()), result);

    }

    @Test
    void deleteUserContactsSuccess(){

        //given
        Mockito.when(userContactsRepository.findByUserIdAndDeleted(userId, false)).thenReturn(Optional.of(userContacts));

        //when
        String result = userContactsService.deleteUserContacts(userId, false);

        //then
        Assertions.assertEquals(String.format("Данные пользователя с ID %s успешно удалены", userContacts.getUserId()), result);

    }

    @Test
    void createUserContactsError(){

        UserContacts userContacts = new UserContacts(999L, "Vasyan221", "+79999999","vasya111@mail.org");

        //given
        Mockito.when(userContactsRepository.save(userContacts)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userContactsService.createUserContacts(userContacts);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);

    }

    @Test
    void deleteUserContactsError() {
        UserContactsRepository userContactsRepository = Mockito.mock(UserContactsRepository.class);
        UserContactsService userContactsService = new UserContactsService(userContactsRepository);

        //given
        Mockito.when(userContactsRepository.findByUserIdAndDeleted(userId, false)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userContactsService.deleteUserContacts(userContacts.getUserId(), false);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void getUserContactsError() {

        UserContactsRepository userContactsRepository = Mockito.mock(UserContactsRepository.class);
        UserContactsService userContactsService = new UserContactsService(userContactsRepository);

        //given
        lenient().when(userContactsRepository.findByUserIdAndDeleted(userId, false)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userContactsService.getUserContacts(userContacts.getUserId(), false);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }

    @Test
    void updateUserContactsError() {

        UserContacts userContacts = new UserContacts(userId, "Vasyan221", "+79999999","vasya111@mail.org");

        UserContactsRepository userContactsRepository = Mockito.mock(UserContactsRepository.class);
        UserContactsService userContactsService = new UserContactsService(userContactsRepository);

        //given
        lenient().when(userContactsRepository.findByUserIdAndDeleted(userId, false)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userContactsService.updateUserContacts(userContacts);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }



}
