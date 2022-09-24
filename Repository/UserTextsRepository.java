package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ru.skillbox.demo.entity.UserText;

import javax.transaction.Transactional;
import java.util.List;

public interface UserTextsRepository extends CrudRepository<UserText, Long> {

   //List<UserText> findById(long id);

    @Transactional
    void deleteById(long Id);
}
