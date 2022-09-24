package ru.skillbox.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.UserContacts;
import ru.skillbox.demo.service.UserContactsService;

@RestController
@RequestMapping(value="/userContacts")
public class UserContactsController {
    private final UserContactsService UserContactsService;

    public UserContactsController(UserContactsService UserContactsService) {
        this.UserContactsService = UserContactsService;
    }

    @PostMapping
    String createUserContacts (@RequestBody UserContacts userContacts){
        return UserContactsService.createUserContacts(userContacts);

    }

    @GetMapping
    UserContacts getUserContacts (@RequestParam Long userId, @RequestParam boolean deleted) {
        return UserContactsService.getUserContacts(userId, false);
    }

    @PutMapping
    String updateUserContacts(@RequestBody UserContacts userContacts) {
        return UserContactsService.updateUserContacts(userContacts);

    }


    @DeleteMapping
    String deleteUserContacts(@RequestParam long userId) {
        return UserContactsService.deleteUserContacts(userId, false);
    }


}

