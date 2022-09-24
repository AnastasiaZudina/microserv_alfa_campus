package ru.skillbox.demo.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="USERINFO")
@SQLDelete(sql = "UPDATE UserInfo SET deleted = true WHERE ID=?")
@Where(clause = "deleted=false")

public class UserInfo {

    @Id
    @GeneratedValue (generator = "long")
    @Column(name = "ID")
    private long id;


    @JoinColumn(name = "USERID", insertable =false, updatable =false)
    User ob;

    //
    @Column (name= "USERID")
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

    @PreRemove
    public void setDeleted() {
        this.deleted = deleted;
    }

    // конструктор
    public UserInfo(){

    }

    public UserInfo(Long userId, String name, String fam, String otch, String gender, String birthDate, String city) {
        this.userId = userId;
        this.name = name;
        this.fam = fam;
        this.otch = otch;
        this.gender = gender;
        this.birthDate = birthDate;
        this.city = city;
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
