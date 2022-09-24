package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillbox.demo.entity.Subscription;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    //List<User> findById(Long id);
    List<Subscription> findAll();

    Optional<Subscription> findById(Long Id);

    boolean existsById(Long Id);


    void deleteById(Long Id);

    boolean existsByUserPrevAndUserNext(Long userPrev, Long userNext);

    Optional<Subscription> findByUserPrevAndUserNext(Long userPrev, Long userNext);

    @Transactional
    void deleteByUserPrevAndUserNext(Long userPrev, Long userNext);
}
