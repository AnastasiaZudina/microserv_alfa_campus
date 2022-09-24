package ru.skillbox.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skillbox.demo.entity.Subscription;
import ru.skillbox.demo.repository.SubscriptionRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private final SubscriptionRepository SubscriptionRepository;
    private EntityManager entityManager;

    @Autowired
    private UserService UserService;

    public SubscriptionService(SubscriptionRepository SubscriptionRepository) {
        this.SubscriptionRepository = SubscriptionRepository;
    }

    public String createSubscription(Subscription Subscription) {
        Optional UserPrev = UserService.findUserById(Subscription.getUserPrev());
        Optional UserNext = UserService.findUserById(Subscription.getUserNext());
        if (UserPrev==Optional.empty() || UserNext==Optional.empty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        boolean Subscription2 = SubscriptionRepository.existsByUserPrevAndUserNext(Subscription.getUserPrev(), Subscription.getUserNext());
        if (Subscription2 == true) {
            return String.format( "Такая подписка уже существует");
        }

        Subscription savedSubscription = SubscriptionRepository.save(Subscription);
        return String.format("Подписка создана");
    }

    public Subscription getSubscription(long userPrev, long userNext) {
        return SubscriptionRepository.findByUserPrevAndUserNext(userPrev, userNext).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String updateSubscription(Subscription Subscription){
        Optional UserPrev = UserService.findUserById(Subscription.getUserPrev());
        Optional UserNext = UserService.findUserById(Subscription.getUserNext());
        if (UserPrev==Optional.empty() || UserNext==Optional.empty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        boolean Subscription2 = SubscriptionRepository.existsByUserPrevAndUserNext(Subscription.getUserPrev(), Subscription.getUserNext());
        if (Subscription2 == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        SubscriptionRepository.deleteByUserPrevAndUserNext(Subscription.getUserPrev(),Subscription.getUserNext());
        Subscription savedSubscription = SubscriptionRepository.save(Subscription);
        return String.format("Подписка обновлена");
    }

    public String deleteSubscription(long userPrev, long userNext){
        boolean Subscription = SubscriptionRepository.existsByUserPrevAndUserNext(userPrev,userNext);
        if (Subscription == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        SubscriptionRepository.deleteByUserPrevAndUserNext(userPrev,userNext);
        return String.format("Подписка удалена");
    }


}
