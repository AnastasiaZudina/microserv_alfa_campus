package ru.skillbox.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value="/users")
public class UserController {

    private final UserService UserService;

    public UserController(UserService userService) {
        this.UserService = userService;
    }

    @PostMapping
    String createUser (@RequestBody User user){
        return UserService.createUser(user);
    }

    @GetMapping(path="/{userId}")
    User getUser(@PathVariable long userId) {
        return UserService.getUser(userId);
    }

   @PutMapping(path="/{userId}")
    String updateUser(@RequestBody User user, @PathVariable long userId) {
        return UserService.updateUser(user, userId);
    }

    @DeleteMapping(path="/{userId}")
    String deleteUser(@PathVariable long userId) {
        return UserService.deleteUser(userId);
    }

    @GetMapping
    List<User> getUsers() {
        return UserService.getUsers();
    }


}
