package ru.skillbox.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.UserInfo;
import ru.skillbox.demo.entity.UserPics;
import ru.skillbox.demo.repository.UserPicsRepository;

import java.util.Optional;

@Service

public class UserPicsService {

    private final ru.skillbox.demo.repository.UserPicsRepository UserPicsRepository;

    @Autowired
    private UserService UserService;

    public UserPicsService(UserPicsRepository UserPicsRepository) {
        this.UserPicsRepository = UserPicsRepository;
    }

    public String createUserPics(UserPics UserPics) {
        Optional User = UserService.findUserById(UserPics.getUserId());
        if (User == Optional.empty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!UserPicsRepository.findByUserId(UserPics.getUserId()).isPresent()){
            UserPics savedUserPics = UserPicsRepository.save(UserPics);
            return String.format("Картинка пользователя с ID %s добавлена в базу", savedUserPics.getUserId());
        }

        return String.format("картинка пользователя ID %s уже заведена" , UserPics.getUserId());
    }

    public UserPics getUserPics(long userId) {
        return UserPicsRepository.findByUserId(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public String updateUserPics(UserPics UserPics) {
        if (!UserPicsRepository.existsByUserId(UserPics.getUserId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserPics savedUserPics = UserPicsRepository.save(UserPics);
        return String.format("Данные пользователя c ID %s сохранены", savedUserPics.getUserId());
    }

    public String deleteUserPics(long userId) {
        if (!UserPicsRepository.existsByUserId(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserPicsRepository.deleteByUserId(userId);
        return String.format("Картинка удалена");
    }


}
