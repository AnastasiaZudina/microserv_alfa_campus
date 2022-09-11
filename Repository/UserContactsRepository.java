package ru.skillbox.demo.repository;
import org.springframework.data.repository.CrudRepository;

import ru.skillbox.demo.entity.UserContacts;
import ru.skillbox.demo.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserContactsRepository extends CrudRepository<UserContacts, Long> {

    List<UserContacts> findByNickname(String Nickname);
    Optional<UserInfo> findByUserIdAndDeleted(long userId, boolean deleted) ;

    Optional<UserInfo> existsByUserIdAndDeleted(long userId, boolean deleted);

    void deleteByUserIdAndDeleted(long userId, boolean b);

    UserContacts save(UserContacts userContacts);
}
