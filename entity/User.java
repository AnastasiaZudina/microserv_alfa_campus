package ru.skillbox.demo.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;



@Entity
@Table(name = "User")
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE userId=?")//, check = ResultCheckStyle.COUNT)
@Where(clause = "deleted=false")

public class User implements Serializable {

    //
    @Id
    @GeneratedValue(generator = "long")

    @Column (name="USERID")
    private long userId;
    @Column (name="deleted")
    private Boolean deleted = Boolean.FALSE;

    //

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean getDeleted() {
        return deleted;
    }

    @PreRemove
    public void setDeleted() {
        this.deleted = deleted;
    }

    //конструктор
    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                '}';
    }

}
