-- создание базы данных
---CREATE DATABASE users;

-- создание схемы
CREATE SCHEMA IF NOT EXISTS users_scheme;

--Создание таблиц
CREATE TABLE IF NOT EXISTS  users_scheme.user(
    ID uuid PRIMARY KEY NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS  users_scheme.user_pers_info(
    ID uuid PRIMARY KEY NOT NULL,
    USER_ID uuid NOT NULL,
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

CREATE TABLE IF NOT EXISTS  users_scheme.user_contacts(
    ID uuid PRIMARY KEY NOT NULL,
    USER_ID uuid NOT NULL,    
    NICKNAME varchar(120),
    PHONE_NUM varchar (20) NOT NULL,
    MAIL_ADDRESS varchar(120) NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS  users_scheme.user_pics(
    USER_ID uuid PRIMARY KEY NOT NULL,
    UPICK varchar(500)
);

CREATE TABLE IF NOT EXISTS  users_scheme.user_skills(
    ID uuid PRIMARY KEY NOT NULL,  
    USER_ID uuid NOT NULL,
    USKILL varchar(120)
);

CREATE TABLE IF NOT EXISTS  users_scheme.user_texts(
    USER_ID uuid PRIMARY KEY NOT NULL,
    UTEXT varchar(1000)
);

CREATE TABLE IF NOT EXISTS  users_scheme.user_relations(
    IDPREV uuid NOT NULL,
    IDNEXT uuid NOT NULL,
    LKTYPE varchar(6) NOT NULL,
    DELETED bit,
    BEDGA TIMESTAMP NOT NULL,
    ENDDA TIMESTAMP NOT NULL
);


--Идексы таблицы companies_addresses
ALTER TABLE users_scheme.user_relations
    ADD FOREIGN KEY (IDPREV)
        REFERENCES users_scheme.user(id);
        
ALTER TABLE users_scheme.user_relations
    ADD FOREIGN KEY (IDNEXT)
        REFERENCES users_scheme.user(id);

ALTER TABLE users_scheme.user_pers_info
    ADD FOREIGN KEY (USER_ID)
        REFERENCES users_scheme.user(id);

ALTER TABLE users_scheme.user_contacts
    ADD FOREIGN KEY (USER_ID)
        REFERENCES users_scheme.user(id);
        
ALTER TABLE users_scheme.user_pics
    ADD FOREIGN KEY (USER_ID)
        REFERENCES users_scheme.user(id);     

ALTER TABLE users_scheme.user_texts
    ADD FOREIGN KEY (USER_ID)
        REFERENCES users_scheme.user(id); 
        
ALTER TABLE users_scheme.user_skills
    ADD FOREIGN KEY (USER_ID)
        REFERENCES users_scheme.user(id);         