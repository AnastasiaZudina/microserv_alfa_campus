package ru.skillbox.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.UserSkills;
import ru.skillbox.demo.entity.UserText;

import java.util.List;

@Service
public class UserSkillsService {
    private final ru.skillbox.demo.repository.UserSkillsRepository UserSkillsRepository;

    @Autowired
    private UserService UserService;


    public UserSkillsService(ru.skillbox.demo.repository.UserSkillsRepository userSkillsRepository) {
        UserSkillsRepository = userSkillsRepository;
    }

    public String createUserSkills(UserSkills userSkills) {
        UserSkills savedUserSkills = UserSkillsRepository.save(userSkills);
        return String.format("Навык пользователя ID %s добавлен в базу" , savedUserSkills.getUserId());
    }

    public List<UserSkills> getUserSkills(long userId) {
        if (!UserSkillsRepository.existsByUserId(userId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return UserSkillsRepository.findAllByUserId(userId);
    }

    public String updateUserSkills(UserSkills UserSkills) {
        if (!UserSkillsRepository.existsById(UserSkills.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserSkillsRepository.deleteById(UserSkills.getId());
        UserSkills savedUserSkills = UserSkillsRepository.save(UserSkills);
        return String.format("Навык пользователя c ID %s сохранен", savedUserSkills.getId());
    }

    public String deleteUserSkills(String uskill, long userId) {
        if (!UserSkillsRepository.existsByUserIdAndUskill(userId, uskill)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserSkills UserSkills = UserSkillsRepository.findByUserIdAndUskill(userId, uskill);
        UserSkillsRepository.deleteById(UserSkills.getId());
        return String.format("Навык пользователя с ID %s успешно удален", UserSkills.getUserId());
    }


}
