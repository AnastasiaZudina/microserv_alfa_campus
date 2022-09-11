package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillbox.demo.entity.Subscription;
import ru.skillbox.demo.entity.UserSkills;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserSkillsRepository extends CrudRepository<UserSkills, Long> {

        Optional<UserSkills> findByUserIdAndUskill(long userId, String uskill);

        List<UserSkills> findAllByUserId(long userId);

        List<UserSkills> findAll();

        @Transactional
        void deleteByUserIdAndUskill(long userId, String uskill);
}
