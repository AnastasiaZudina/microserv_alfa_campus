package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ru.skillbox.demo.entity.UserPics;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserPicsRepository extends CrudRepository<UserPics, Long> {
    Optional<UserPics> findByUserId(long userId);

    boolean existsByUserId(long userId);

    @Transactional
    void deleteByUserId(long userId);

    // <id> List<UserPics> findById(long id);
}
