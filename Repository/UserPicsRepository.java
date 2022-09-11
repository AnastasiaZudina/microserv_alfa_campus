package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ru.skillbox.demo.entity.UserPics;

import java.util.List;

public interface UserPicsRepository extends CrudRepository<UserPics, Long> {

   // <id> List<UserPics> findById(long id);
}
