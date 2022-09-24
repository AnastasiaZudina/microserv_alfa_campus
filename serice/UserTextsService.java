package ru.skillbox.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.UserPics;
import ru.skillbox.demo.entity.UserText;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserTextsService {
    private final ru.skillbox.demo.repository.UserTextsRepository UserTextsRepository;

    @Autowired
    private UserService UserService;

    public UserTextsService(ru.skillbox.demo.repository.UserTextsRepository userTextsRepository) {
        UserTextsRepository = userTextsRepository;
    }

    public String createUserText(UserText UserText) {
        Optional User = UserService.findUserById(UserText.getId());
        if (User == Optional.empty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!UserTextsRepository.findById(UserText.getId()).isPresent()){
            UserText savedUserText = UserTextsRepository.save(UserText);
            return String.format("Текст пользователя с ID %s добавлен в базу", savedUserText.getId());
        }

        return String.format("Текст пользователя ID %s уже заведен" , UserText.getId());
    }

    public UserText getUserText(long id) {
        return UserTextsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public String updateUserText(UserText UserText) {
        if (!UserTextsRepository.existsById(UserText.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserText savedUserText = UserTextsRepository.save(UserText);
        return String.format("Текст пользователя c ID %s сохранен", savedUserText.getId());
    }

    public String deleteUserText(long id) {
        if (!UserTextsRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserTextsRepository.deleteById(id);
        return String.format("Текст пользователя успешно удален", id);
    }



}
