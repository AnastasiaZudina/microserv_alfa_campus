package ru.skillbox.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.UserPics;
import ru.skillbox.demo.entity.UserText;
import ru.skillbox.demo.service.UserPicsService;
import ru.skillbox.demo.service.UserTextsService;

@RestController
@RequestMapping(value="/userTexts")
public class UserTextsController {

    private final UserTextsService UserTextsService;

    public UserTextsController(UserTextsService UserTextsService) {
        this.UserTextsService = UserTextsService;
    }

    @PostMapping
    String createUserTexts (@RequestBody UserText userText){
        return UserTextsService.createUserText(userText);
    }

    @GetMapping
    UserText getUserTexts (@RequestParam long userId) {
        return UserTextsService.getUserText(userId);
    }

    @PutMapping
    String updateUserTexts(@RequestBody UserText userText) {
        return UserTextsService.updateUserText(userText);
    }

    @DeleteMapping
    String deleteUserText(@RequestParam long userId) {
        return UserTextsService.deleteUserText(userId);
    }



}
