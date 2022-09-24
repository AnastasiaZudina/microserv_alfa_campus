package ru.skillbox.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.UserInfo;
import ru.skillbox.demo.service.UserInfoService;

import java.util.List;

@RestController
@RequestMapping(value="/userInfo")
public class UserInfoController {

    private final UserInfoService UserInfoService;

    public UserInfoController(UserInfoService UserInfoService) {
        this.UserInfoService = UserInfoService;
    }

    @PostMapping
    String createUserInfo (@RequestBody UserInfo userInfo){
        return UserInfoService.createUserInfo(userInfo);
    }

    @GetMapping
    UserInfo getUserInfo (@RequestParam long userId, @RequestParam boolean deleted) {
        return UserInfoService.getUserInfo(userId, false);
    }

   @PutMapping
    String updateUserInfo(@RequestBody UserInfo UserInfo) {
        return UserInfoService.updateUserInfo(UserInfo);
    }

    @DeleteMapping
    String deleteUserInfo(@RequestParam long userId) {
        return UserInfoService.deleteUserInfo(userId, false);
    }



}
