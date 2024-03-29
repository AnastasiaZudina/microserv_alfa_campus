package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillbox.demo.entity.Subscription;
import ru.skillbox.demo.entity.UserSkills;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserSkillsRepository extends CrudRepository<UserSkills, Long> {

        boolean existsById(long Id);

        List<UserSkills> findAllByUserId(Long userId);

        List<UserSkills> findAll();

        @Transactional
        void deleteByUserIdAndUskill(Long userId, String uskill);

        Optional<UserSkills> existsByUserId(Long userId);

        boolean existsByUserIdAndUskill(Long userId, String uskill);

        UserSkills findByUserIdAndUskill(Long userId, String uskill);
}
