package ru.skillbox.demo.service;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.skillbox.demo.entity.UserContacts;
import ru.skillbox.demo.entity.UserInfo;

import java.util.List;
import java.util.Optional;

@Service
public class UserContactsService {

    @Autowired
    private UserService UserService;

    private final ru.skillbox.demo.repository.UserContactsRepository UserContactsRepository;

    public UserContactsService(ru.skillbox.demo.repository.UserContactsRepository UserContactsRepository) {
        this.UserContactsRepository = UserContactsRepository;
    }

    public String createUserContacts(@NotNull UserContacts UserContacts) {
        Optional User = UserService.findUserById(UserContacts.getUserId());
        if (User == Optional.empty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!UserContactsRepository.findByUserIdAndDeleted(UserContacts.getUserId(), false).isPresent()){
            UserContacts savedUserContacts = UserContactsRepository.save(UserContacts);
            return String.format("Данные пользователя с ID %s добавлены в базу", savedUserContacts.getUserId());
        }
        return String.format("Данные пользователя с ID %s уже заведены", UserContacts.getUserId());
    }

    public UserContacts getUserContacts(long userId, boolean deleted) {
        return UserContactsRepository.findByUserIdAndDeleted(userId, false).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public String updateUserContacts(UserContacts UserContacts) {

        if (!UserContactsRepository.existsByUserIdAndDeleted(UserContacts.getUserId(), false).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserContactsRepository.deleteByUserIdAndDeleted(UserContacts.getUserId(), false);
        UserContacts savedUserContacts = UserContactsRepository.save(UserContacts);
        return String.format("данные пользователя с ID %s сохранены", savedUserContacts.getUserId());
    }

    public String deleteUserContacts(long userId, boolean deleted) {
        if (!UserContactsRepository.existsByUserIdAndDeleted(userId, false).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserContactsRepository.deleteByUserIdAndDeleted(userId, false);
        return String.format("Данные пользователя с ID %s успешно удалены", userId);
    }

//    public List<UserContacts> getAllUserContacts() {
//        return UserContactsRepository.findAll();
//
//    }



}
