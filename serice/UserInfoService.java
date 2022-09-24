package ru.skillbox.demo.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.entity.UserInfo;
import ru.skillbox.demo.repository.UserInfoRepository;
import ru.skillbox.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserInfoService {


    private final UserInfoRepository UserInfoRepository;

    @Autowired
    private UserService UserService;

    public UserInfoService(UserInfoRepository UserInfoRepository) {
        this.UserInfoRepository = UserInfoRepository;
    }


    public String createUserInfo(@NotNull UserInfo UserInfo) {
        Optional User = UserService.findUserById(UserInfo.getUserId());
        if (User == Optional.empty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!UserInfoRepository.findByUserIdAndDeleted(UserInfo.getUserId(), false).isPresent()){
            UserInfo savedUserInfo = UserInfoRepository.save(UserInfo);
            return String.format("Данные пользователя %s с ID %s добавлены в базу", savedUserInfo.getFam(), savedUserInfo.getUserId());
        }
        return String.format("Данные пользователя с ID %s уже заведены", UserInfo.getUserId());
    }

    public UserInfo getUserInfo(long userId, boolean deleted) {
        return UserInfoRepository.findByUserIdAndDeleted(userId, false).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public String updateUserInfo(UserInfo UserInfo) {

        if (!UserInfoRepository.existsByUserIdAndDeleted(UserInfo.getUserId(), false).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserInfoRepository.deleteByUserIdAndDeleted(UserInfo.getUserId(), false);
        UserInfo savedUserInfo = UserInfoRepository.save(UserInfo);
        return String.format("данные пользователя %s с ID %s сохранены", savedUserInfo.getFam(), savedUserInfo.getUserId());
    }

    public String deleteUserInfo(long userId, boolean deleted) {
        if (!UserInfoRepository.existsByUserIdAndDeleted(userId, false).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserInfoRepository.deleteByUserIdAndDeleted(userId, false);
        return String.format("Данные пользователя с ID %s успешно удалены", userId);
    }

    public List<UserInfo> getUserInfos() {
        return UserInfoRepository.findAll();

    }




}
