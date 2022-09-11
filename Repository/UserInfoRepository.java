package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ru.skillbox.demo.entity.UserInfo;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    List<UserInfo> findByFam(String Fam);

    //  Optional<UserInfo> existsByUserIdTrue(long userId) ;
    Optional<UserInfo> findByUserIdAndDeleted(long userId, boolean deleted) ;

    Optional<UserInfo> existsByUserIdAndDeleted(long userId, boolean deleted);

    void deleteByUserIdAndDeleted(long userId, boolean b);
}
