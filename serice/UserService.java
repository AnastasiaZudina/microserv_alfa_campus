package ru.skillbox.demo.service;
import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private final UserRepository UserRepository;
    private EntityManager entityManager;

    public UserService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public String createUser(User user) {
        User savedUser = UserRepository.save(user);
        return String.format("Пользователь добавлен в базу с ID %s", savedUser.getUserId());
    }

    public User getUser(long userId) {
        return UserRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String updateUser(User user, long userId) {
        if (!UserRepository.existsByUserId(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User savedUser = UserRepository.save(user);
        return String.format("Пользователь с ID %s сохранен", savedUser.getUserId());
    }

    public String deleteUser(long userId) {
        if (!UserRepository.existsByUserId(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserRepository.deleteByUserId(userId);
        return String.format("Пользователь с ID %s успешно удален", userId);
    }

    public List<User> getUsers() {
        return UserRepository.findAll();

    }

    public Optional findUserById(Long userId) {
            return UserRepository.findById(userId);
    }


//    public Iterable<User> getUsers() {
//        Session session = EntityManager.<Session>unwrap(Session.class);
//        Filter filter = session.enableFilter("deletedUsersFilter");
//        filter.setParameter("deleted", false);
//        Iterable<User> users = UserRepository.findAll();
//        session.disableFilter("deletedUser");
//        return users;
//
//    }


}