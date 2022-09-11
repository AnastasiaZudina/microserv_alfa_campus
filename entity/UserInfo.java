package ru.skillbox.demo.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="UserInfo")
@SQLDelete(sql = "UPDATE UserInfo SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class UserInfo {

    @Id
    @GeneratedValue (generator = "long")
    @Column(name = "id")
    private long id;


    @JoinColumn(name = "userId", insertable =false, updatable =false)
    User ob;

    //
    @Column (name= "userId")
    private long userId;

    @Column (name= "name")
    private String name;

    @Column (name= "fam")
    private String fam;

    @Column (name= "otch")
    private String otch;

    @Column (name= "gender")
    private String gender;

    @Column (name= "birthDate")
    private String birthDate;

    @Column (name= "city")
    private String city;

    @Column (name= "deleted")
    private Boolean deleted = Boolean.FALSE;


    //
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {return name;
    }
    public String getFam() {
        return fam;
    }

    public String getOtch() {
        return otch;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    // конструктор
    public UserInfo(){

    }

    public UserInfo(Long userId, String name, String fam, String otch, String gender, String birthDate, String city, boolean deleted) {
        this.userId = userId;
        this.name = name;
        this.fam = fam;
        this.otch = otch;
        this.gender = gender;
        this.birthDate = birthDate;
        this.city = city;
        this.deleted=deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId ='" + userId + '\'' +
                ", Name ='" + name + '\'' +
                ", Fam ='" + fam + '\'' +
                ", Otch ='" + otch + '\'' +
                ", Birth_date ='" + birthDate + '\'' +
                ", Gender ='" + gender + '\'' +
                ", City ='" + city + '\'' +
                ", deleted ='" + deleted + '\'' +
                '}';
    }

}
