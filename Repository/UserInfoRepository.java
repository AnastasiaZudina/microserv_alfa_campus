package ru.skillbox.demo.repository;

import org.springframework.data.repository.CrudRepository;

import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.entity.UserInfo;

import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    List<UserInfo> findByFam(String Fam);
    List<UserInfo> findAll();

    //  Optional<UserInfo> existsByUserIdTrue(long userId) ;
    Optional <UserInfo> findByUserId(Long userId) ;

    Optional <UserInfo> existsByUserIdAndDeleted(Long userId, boolean deleted);

    @Transactional
    void deleteByUserIdAndDeleted(Long userId, boolean b);

    Optional <UserInfo> findByUserIdAndDeleted(Long userId, boolean b);
}
