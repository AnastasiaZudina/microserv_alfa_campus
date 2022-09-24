package ru.skillbox.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "UserTexts")
public class UserText {

        @OneToOne
        @JoinColumn(name = "userId", insertable =false, updatable =false)
        User ob;

        //
        @Id
        @Column (name="userId")
        private long id;

        @Column (name="utext")
        private String utext;

        //

        public long getId() {
            return id;
        }

        public String getUtext() {return utext;
        }

        //конструктор
        public UserText(){
        }

        public UserText(long id, String utext) {
            this.id = id;
            this.utext = utext;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", text ='" + utext + '\'' +
                    '}';
        }


}
