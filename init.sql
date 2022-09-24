-- создание базы данных
---CREATE DATABASE users;

-- создание схемы
CREATE SCHEMA IF NOT EXISTS users_scheme;

--Создание таблиц
CREATE TABLE IF NOT EXISTS  users_scheme.user(
    Id uuid PRIMARY KEY NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS  users_scheme.UserInfo(
    Id int PRIMARY KEY NOT NULL,
    NAME varchar(120) NOT null,
    FAM varchar(120) NOT null,
    OTCH varchar(120),
    GENDER varchar(120),
    BIRTH_DATE date NOT null,
    CITY varchar(120) NOT null, 
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS  users_scheme.UserContacts(
    Id int PRIMARY KEY NOT NULL,    
    NICKNAME varchar(120),
    phoneNum varchar (20) NOT NULL,
    mailAddress varchar(120) NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS  users_scheme.UserPics(
    Id int PRIMARY KEY NOT NULL,
    UPIC varchar(500)
);

CREATE TABLE IF NOT EXISTS  users_scheme.UserSkills(
    Id int PRIMARY KEY NOT NULL,  
    USKILL varchar(120)
);

CREATE TABLE IF NOT EXISTS  users_scheme.UserTexts(
    Id int PRIMARY KEY NOT NULL,
    UTEXT varchar(1000)
);

CREATE TABLE IF NOT EXISTS  users_scheme.Subscriptions(
	Id int PRIMARY KEY NOT NULL,
    UserPrev int NOT NULL,
    UserNext int NOT NULL,
    LKTYPE varchar(6) NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS users_scheme.gender(
    ID varchar (1) PRIMARY KEY NOT NULL,
    G_TEXT varchar(10) NOT NULL
    
);    

--Ограничения

ALTER TABLE users_scheme.userInfo
    ADD FOREIGN KEY (ID)
        REFERENCES users_scheme.user(id)
        ON DELETE NO ACTION;

ALTER TABLE users_scheme.userContacts
    ADD FOREIGN KEY (ID)
        REFERENCES users_scheme.user(id)
        ON DELETE NO ACTION;
        
ALTER TABLE users_scheme.userPics
    ADD FOREIGN KEY (ID)
        REFERENCES users_scheme.user(id)
        ON DELETE CASCADE;     

ALTER TABLE users_scheme.userTexts
    ADD FOREIGN KEY (ID)
        REFERENCES users_scheme.user(id)
        ON DELETE CASCADE; 
        
ALTER TABLE users_scheme.userSkills
    ADD FOREIGN KEY (ID)
        REFERENCES users_scheme.user(id)
        ON DELETE CASCADE;
        
ALTER TABLE users_scheme.userInfo
    ADD FOREIGN KEY (GENDER)
        REFERENCES users_scheme.gender(id)
        ON DELETE RESTRICT;

--Индексы
CREATE INDEX i_city 
ON users_scheme.userInfo (city);

CREATE INDEX i_gender
ON users_scheme.userInfo (gender);

CREATE INDEX i_tinder
ON users_scheme.userInfo  (gender, city);

CREATE INDEX i_nick
ON users_scheme.userContacts (nickname);

CREATE INDEX i_links
ON users_scheme.subcriptions (UserPrev, lktype);

