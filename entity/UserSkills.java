package ru.skillbox.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "UserSkills")
public class UserSkills {
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable =false, updatable =false)
    User ob;

    //
    @Column(name= "userId")
    private long userId;

    @Column(name = "id")
    @Id
    @GeneratedValue (generator="long")
    private long id;

    @Column(name= "uSkill")
    private String uskill;


    //

    public String getUskill() {
        return uskill;
    }

    //

    public UserSkills(){
    }

    public UserSkills(long userId, String uskill) {
        this.userId = userId;
        this.uskill = uskill;
    }

    @Override
    public String toString() {
        return "User Skill{" +
                "userId=" + userId +
                ", Id=" + id +
                ", Skill='" + uskill + '\'' +
                '}';
    }
}
