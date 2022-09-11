package ru.skillbox.demo.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity (name="Subscription")
@Table(name = "Subscriptions")
@SQLDelete(sql = "UPDATE UserSubscription SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Subscription {

    @EmbeddedId
    private SubscriptionId id;

    @Column(name="LkType")
    private String LkType;

    @Column(name="deleted")
    private Boolean deleted = Boolean.FALSE;

    //
    public SubscriptionId getId() {
        return id;
    }

    public void setId(SubscriptionId id) {
        this.id = id;
    }

    public String getLkType() {
        return LkType;
    }

    public boolean getDeleted() {
        return deleted;
    }

    @PreRemove
    public void setDeleted() {
        this.deleted = deleted;
    }

    //конструктор

    public Subscription(){
    }

    public Subscription(SubscriptionId id, String LkType , boolean deleted) {
        this.id = id;
        this.LkType = LkType;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserSubscription{" +
                "id=" + id +
                ", link type=" + LkType +
                ", deleted=" + deleted +
                '}';
    }


}
