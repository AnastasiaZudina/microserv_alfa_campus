package ru.skillbox.demo.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity (name="Subscription")
@Table(name = "Subscriptions")
@SQLDelete(sql = "UPDATE Subscriptions SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Subscription {

    @Id
    @GeneratedValue(generator = "long")

    @Column (name="ID")
    private long id;

    @Column(name = "UserPrev")
    private Long userPrev;
    @Column(name = "UserNext")
    private Long userNext;

    @Column(name="LkType")
    private String lkType;

    @Column(name="deleted")
    private Boolean deleted = Boolean.FALSE;

    //

    public long getId() {
        return id;
    }


    public void setLkType() {
        this.lkType = lkType;
    }

    public String getLkType() {
        return lkType;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public Long getUserPrev() {
        return userPrev;
    }

    public void setUserPrev(Long userPrev) {
        this.userPrev = userPrev;
    }

    public Long getUserNext() {
        return userNext;
    }

    public void setUserNext(Long userNext) {
        this.userNext = userNext;
    }

    @PreRemove
    public void setDeleted() {
        this.deleted = deleted;
    }

    //конструктор

    public Subscription(){
    }

    public Subscription(long userPrev,long userNext, String lkType) {
        this.userPrev = userPrev;
        this.userNext = userNext;
        this.lkType = lkType;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", prev=" + userPrev +
                ", next =" + userNext +
                ", link type=" + lkType +
                ", deleted=" + deleted +
                '}';
    }


}
