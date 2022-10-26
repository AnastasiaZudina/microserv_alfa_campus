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
import ru.skillbox.demo.entity.UserText;
import ru.skillbox.demo.repository.UserTextsRepository;

import javax.persistence.PersistenceException;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserTextsServiceTest {
    private Long userId;
    private UserText userText;

    @Autowired
    @InjectMocks
    private UserTextsService userTextsService;

    @Mock
    private UserTextsRepository userTextsRepository;

    @DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

    @BeforeEach
    void setUp(){
        userId = 2L;
        userText = new UserText(userId, "в чаше юга жил-был цитрус");
    }

    @Test
    void createUserTextSuccess() {

        //given
        UserText userText =  new UserText(userId, "в чаше юга жил-был цитрус");
        UserText savedUserText = new UserText(userId, "в чаше юга жил-был цитрус");
        Mockito.when(userTextsRepository.save(userText)).thenReturn(savedUserText);

        //when
        String result = userTextsService.createUserText(userText);

        //then
        Assertions.assertEquals(String.format("Текст пользователя с ID %s добавлен в базу", userId), result);

    }

    @Test
    void getUserTextSuccess() {

        userId = 1L;

        //given
        Mockito.when(userTextsRepository.findByUserId(userId)).thenReturn(Optional.of(userText));

        //when
        Long id = userTextsService.getUserText(userId).getUserId();

        //then
        Assertions.assertEquals(userId, id);
    }


    @Test
    void deleteUserTextSuccess(){

        userId = 1L;
        userText = new UserText(userId, "в чаше юга жил-был цитрус");

        //given
        Mockito.when(userTextsRepository.findByUserId(userId)).thenReturn(Optional.of(userText));

        //when
        String result = userTextsService.deleteUserText(userText.getUserId());

        //then
        Assertions.assertEquals("Текст пользователя успешно удален", result);

    }

    @Test
    void createUserTextsError(){

        userText = new UserText(999L, "в чаше юга жил-был цитрус");

        //given
        Mockito.when(userTextsRepository.save(userText)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userTextsService.createUserText(userText);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);

    }

    @Test
    void deleteUserTextError() {
        UserTextsRepository userTextsRepository = Mockito.mock(UserTextsRepository.class);
        UserTextsService userTextsService = new UserTextsService(userTextsRepository);

        //given
        Mockito.when(userTextsRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userTextsService.deleteUserText(userText.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void getUserTextError() {

        UserTextsRepository userTextsRepository = Mockito.mock(UserTextsRepository.class);
        UserTextsService userTextsService = new UserTextsService(userTextsRepository);

        //given
        lenient().when(userTextsRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userTextsService.getUserText(userText.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }

    @Test
    void updateUserTextError() {

        UserText userText = new UserText(userId,"Vasili rulez");

        UserTextsRepository userTextsRepository = Mockito.mock(UserTextsRepository.class);
        UserTextsService userTextsService = new UserTextsService(userTextsRepository);

        //given
        lenient().when(userTextsRepository.findByUserId(userId)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userTextsService.updateUserText(userText);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }




}
