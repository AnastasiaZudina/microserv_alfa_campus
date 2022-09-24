package ru.skillbox.demo.repository;
import org.springframework.data.repository.CrudRepository;

import ru.skillbox.demo.entity.UserContacts;
import ru.skillbox.demo.entity.UserInfo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserContactsRepository extends CrudRepository<UserContacts, Long> {

    List<UserContacts> findByNickname(String Nickname);
    Optional<UserContacts> findByUserIdAndDeleted(Long userId, boolean deleted) ;

    Optional<UserContacts> existsByUserIdAndDeleted(Long userId, boolean deleted);
    @Transactional
    void deleteByUserIdAndDeleted(Long userId, boolean b);

   // UserContacts save(UserContacts userContacts);
}
