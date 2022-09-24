package ru.skillbox.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;
import ru.skillbox.demo.entity.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
        Optional <User> findByUserId(Long userId);
        List<User> findAll();

        boolean existsByUserId(Long userId);
        @Transactional
        void deleteByUserId(Long userId);
}

