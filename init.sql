-- создание базы данных
---CREATE DATABASE users;

-- создание схемы
CREATE SCHEMA IF NOT EXISTS users;

--Создание таблиц
CREATE TABLE IF NOT EXISTS  users.user(
    userId int PRIMARY KEY NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS  users.UserInfo(
    Id int PRIMARY KEY NOT NULL,
	userId int not null,
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

CREATE TABLE IF NOT EXISTS  users.UserContacts(
    Id int PRIMARY KEY NOT NULL, 
	userId int NOT NULL,	
    NICKNAME varchar(120),
    phoneNum varchar (20) NOT NULL,
    mailAddress varchar(120) NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS  users.UserPics(
    userId int PRIMARY KEY NOT NULL,
    UPIC varchar(500)
);

CREATE TABLE IF NOT EXISTS  users.UserSkills(
    Id int PRIMARY KEY NOT NULL,  
	userId int NOT NULL,  
    USKILL varchar(120)
);

CREATE TABLE IF NOT EXISTS  users.UserTexts(
    userId int PRIMARY KEY NOT NULL,
    UTEXT varchar(1000)
);

CREATE TABLE IF NOT EXISTS  users.Subscriptions(
	Id int PRIMARY KEY NOT NULL,
    UserPrev int NOT NULL,
    UserNext int NOT NULL,
    LKTYPE varchar(6) NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS users.gender(
    ID varchar (1) PRIMARY KEY NOT NULL,
    G_TEXT varchar(10) NOT NULL
    
);    

--Ограничения

ALTER TABLE users_.userInfo
    ADD FOREIGN KEY (userId)
        REFERENCES users.user(userId)
        ON DELETE NO ACTION;

ALTER TABLE users.userContacts
    ADD FOREIGN KEY (userId)
        REFERENCES users.user(userId)
        ON DELETE NO ACTION;
        
ALTER TABLE users.userPics
    ADD FOREIGN KEY (userId)
        REFERENCES users.user(userId)
        ON DELETE CASCADE;     

ALTER TABLE users.userTexts
    ADD FOREIGN KEY (userId)
        REFERENCES users.user(userId)
        ON DELETE CASCADE; 
        
ALTER TABLE users.userSkills
    ADD FOREIGN KEY (userId)
        REFERENCES users.user(userId)
        ON DELETE CASCADE;
        
ALTER TABLE users_scheme.userInfo
    ADD FOREIGN KEY (GENDER)
        REFERENCES users.gender(id)
        ON DELETE RESTRICT;

--Индексы
CREATE INDEX i_city 
ON users.userInfo (city);

CREATE INDEX i_gender
ON users.userInfo (gender);

CREATE INDEX i_tinder
ON users.userInfo  (gender, city);

CREATE INDEX i_nick
ON users.userContacts (nickname);

CREATE INDEX i_links
ON users.subcriptions (UserPrev, lktype);

