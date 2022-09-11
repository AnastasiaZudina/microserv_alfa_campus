package ru.skillbox.demo.entity;

import javax.persistence.*;

@Entity
@Table (name = "UserPics")
public class UserPics {

    @OneToOne
    @JoinColumn(name = "userId", insertable =false, updatable =false)
    User ob;

    //
    @Id
    @Column (name="userId")
    private long userId;

    @Column (name="upic")
    private String upic;

    //

    public long getId() {
        return userId;
    }

    public String getUpic() {return upic;
    }

    //конструктор
    public UserPics(){
    }

    public UserPics(long userId, String upic) {
        this.userId = userId;
        this.upic = upic;
    }

    @Override
    public String toString() {
        return "User Picture{" +
                "userId=" + userId +
                ", upiclink='" + upic + '\'' +
                '}';
    }
}
