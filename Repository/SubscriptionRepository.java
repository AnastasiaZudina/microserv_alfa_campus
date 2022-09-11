package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillbox.demo.entity.Subscription;
import ru.skillbox.demo.entity.SubscriptionId;
import ru.skillbox.demo.entity.User;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, SubscriptionId> {
    //List<User> findById(Long id);
    List<Subscription> findAll();
}
