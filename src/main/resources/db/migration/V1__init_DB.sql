create sequence hibernate_sequence start 1 increment 1;

create table users (
    id int8 primary key,
    username varchar(255),
    password varchar(255),
    version bigint,
    active boolean
);

create table user_role (
    user_id int8,
    roles varchar(255)
);