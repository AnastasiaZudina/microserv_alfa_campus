package ru.skillbox.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table (name="Subscriptions")
public class SubscriptionId implements Serializable {
    @Column(name = "UserPrev")
    private Long UserPrev;
    @Column(name = "UserNext")
    private Long UserNext;


    // default constructor
    public SubscriptionId(){

    }

    public SubscriptionId(Long UserPrev, Long UserNext) {
        this.UserPrev = UserPrev;
        this.UserNext = UserNext;
    }
    public Long getUserPrev() {
        return UserPrev;
    }

    public Long getUserNext() {
        return UserNext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionId)) return false;
        SubscriptionId that = (SubscriptionId) o;
        return Objects.equals(getUserPrev(), that.getUserPrev()) &&
                Objects.equals(getUserNext(), that.getUserNext());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserPrev(), getUserNext());
    }

    @Override
    public String toString() {
        return "{" +
                "Prev=" + UserPrev +
                "Next=" + UserNext +
                '}';
    }
}


