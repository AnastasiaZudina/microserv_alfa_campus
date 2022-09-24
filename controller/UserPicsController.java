package ru.skillbox.demo.controller;


import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.UserPics;
import ru.skillbox.demo.service.UserPicsService;

@RestController
@RequestMapping(value="/userPics")
public class UserPicsController {
    private final UserPicsService UserPicsService;

    public UserPicsController(UserPicsService UserPicsService) {
        this.UserPicsService = UserPicsService;
    }

    @PostMapping
    String createUserPics (@RequestBody UserPics userPics){
        return UserPicsService.createUserPics(userPics);
    }

    @GetMapping
    UserPics getUserPics (@RequestParam long userId) {
        return UserPicsService.getUserPics(userId);
    }

    @PutMapping
    String updateUserPics(@RequestBody UserPics userPics) {
        return UserPicsService.updateUserPics(userPics);
    }

    @DeleteMapping
    String deleteUserPics(@RequestParam long userId) {
        return UserPicsService.deleteUserPics(userId);
    }

}
