package ru.skillbox.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.UserSkills;
import ru.skillbox.demo.entity.UserText;
import ru.skillbox.demo.repository.UserSkillsRepository;
import ru.skillbox.demo.repository.UserTextsRepository;

import javax.persistence.PersistenceException;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@RunWith(SpringRunner.class)
@SpringBootTest()

public class UserSkillsServiceTest {

    private Long userId;
    private String skill;
    private UserSkills userSkills;
    private final List<UserSkills> vasyaSkills = new ArrayList<>();
    private UserSkills Vasya1;
    private UserSkills Vasya2;
    private UserSkills Vasya3;

    @Autowired
    @InjectMocks
    private UserSkillsService userSkillsService;

    @Mock
    private UserSkillsRepository userSkillsRepository;

    @DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

    @BeforeEach
    void setUp(){
        userId = 1L;
        skill = "python";
        Vasya1 = new UserSkills(1L, "python" );
        Vasya2 = new UserSkills(1L, "skiing" );
        Vasya3 = new UserSkills(1L, "cross-stich" );
        userSkills = new UserSkills(userId, "python");

        vasyaSkills.add(Vasya1);
        vasyaSkills.add(Vasya2);
        vasyaSkills.add(Vasya3);
    }

    @Test
    void createUserSkillsSuccess() {

        //given

        UserSkills savedUserSkills = new UserSkills(userId, "music");
        Mockito.when(userSkillsRepository.save(userSkills)).thenReturn(savedUserSkills);

        //when
        String result = userSkillsService.createUserSkills(userSkills);

        //then
        Assertions.assertEquals(String.format("Навык пользователя с ID %s добавлен в базу", userId), result);

    }

    @Test
    void getUserSkillsSuccess() {

        //given
        Mockito.when(userSkillsRepository.existsByUserId(userId)).thenReturn(Optional.ofNullable(Vasya1));

        //when
        List<UserSkills> result = userSkillsService.getUserSkills(userId);

        //then
        Assertions.assertEquals(result.get(0).getUserId(), Vasya1.getUserId());
        Assertions.assertEquals(result.get(0).getUskill(), Vasya1.getUskill());
    }


    @Test
    void deleteUserSkillsSuccess(){

        //given
        Mockito.when(userSkillsRepository.findByUserIdAndUskill(userId, skill)).thenReturn(Optional.of(userSkills));

        //when
        String result = userSkillsService.deleteUserSkills(skill, userId);

        //then
        Assertions.assertEquals(String.format("Навык %s пользователя с ID %s успешно удален", skill, userId), result);

    }

    @Test
    void createUserSkillsError(){

        userSkills = new UserSkills(999L, "music");

        //given
        Mockito.when(userSkillsRepository.save(userSkills)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userSkillsService.createUserSkills(userSkills);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);

    }

    @Test
    void deleteUserSkillsError() {
        UserSkillsRepository userSkillsRepository = Mockito.mock(UserSkillsRepository.class);
        UserSkillsService userSkillsService = new UserSkillsService(userSkillsRepository);

        //given
        Mockito.when(userSkillsRepository.findByUserIdAndUskill(userId, skill)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userSkillsService.deleteUserSkills(userSkills.getUskill(), userSkills.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void getUserSkillsError() {

        UserSkillsRepository userSkillsRepository = Mockito.mock(UserSkillsRepository.class);
        UserSkillsService userSkillsService = new UserSkillsService(userSkillsRepository);

        //given
        Mockito.when(userSkillsRepository.findByUserIdAndUskill(userId, skill)).thenThrow(ResponseStatusException.class);

        //when
        Executable executable = () -> userSkillsService.getUserSkills(userSkills.getUserId());

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);


    }

    @Test
    void updateUserPicsError() {

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
