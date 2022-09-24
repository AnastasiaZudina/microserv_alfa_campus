package ru.skillbox.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.UserInfo;
import ru.skillbox.demo.entity.UserSkills;
import ru.skillbox.demo.service.UserInfoService;
import ru.skillbox.demo.service.UserSkillsService;

import java.util.List;

@RestController
@RequestMapping(value="/userSkills")
public class UserSkillsController {

    private final UserSkillsService UserSkillsService;

    public UserSkillsController(UserSkillsService UserSkillsService) {
        this.UserSkillsService = UserSkillsService;
    }

    @PostMapping
    String createUserSkills (@RequestBody UserSkills userSkills){
        return UserSkillsService.createUserSkills(userSkills);
    }

    @GetMapping
    List<UserSkills> getUserSkills(@RequestParam long userId) {
        return UserSkillsService.getUserSkills(userId);
    }

    @PutMapping
    String updateUserSkills(@RequestBody UserSkills UserSkills) {
        return UserSkillsService.updateUserSkills(UserSkills);
    }

    @DeleteMapping
    String deleteUserSkills(@RequestParam long userId, @RequestParam String uskill) {
        return UserSkillsService.deleteUserSkills(uskill, userId);
    }




}
