--liquibase formatted sql

--changeset liquibase:1
create table user (
    id int primary key identity(1,1),
    name varchar(50),
    email varchar(50),
    hashcode varchar(50),
    iconname varchar(50)
);

--changeset liquibase:2
create table games (
    id int primary key identity(1,1),
    title varchar(50),
    description varchar(500),
    price int,
    imagename varchar(50)
);

--changeset liquibase:3
insert into  games ( title, description, price, imagename )
    values ( 'The long dark',
    ' The Long Dark - это исследовательская игра-симулятор выживания',
    350, '')
);