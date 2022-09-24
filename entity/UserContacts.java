package ru.skillbox.demo.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity (name="UserContacts")
@Table(name = "USERCONTACTS")
@SQLDelete(sql = "UPDATE UserContacts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class UserContacts  {

    @Id
    @GeneratedValue(generator = "long")
    @Column (name= "ID")
    private long id;


    @JoinColumn(name = "userId", insertable =false, updatable =false)
    User ob;

    //

    @Column (name= "userId")
    private long userId;

    //

    @Column (name="nickname")
    private String nickname;

    @Column (name="phoneNum")
    private String phoneNum;

    @Column (name="mailAddress")
    private String mailAddress;

    @Column (name= "deleted")
    private Boolean deleted = Boolean.FALSE;


    //
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getNickname() {return nickname;
    }

    public String getPhoneNum() {return phoneNum;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    @PreRemove
    public void setDeleted() {
        this.deleted = deleted;
    }

    //конструктор

    public UserContacts(){

    }

    public UserContacts(Long userId, String nickname, String phoneNum, String mailAddress) {
       // this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return "UserContact{" +
                "Id=" + id +
                ", userId ='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone ='" + phoneNum + '\'' +
                ", mailAddress ='" + mailAddress + '\'' +
                ", deleted ='" + deleted + '\'' +
                '}';
    }
}
