-- liquibase formatted sql
-- changeset Andrey:1

create table users (
id serial primary key,
password varchar(64),
first_name text,
last_name text,
email varchar(32) not null,
phone text,
role text
);

create table ad (
id serial primary key ,
author_id int not null,
price int not null ,
title text,
description text,
foreign key (author_id) references users(id) on delete cascade
);

create table comment (
id serial primary key ,
ad_id int,
author_id int,
created_at timestamp,
text_comment text not null ,
foreign key (ad_id) references ad (id) on delete cascade,
foreign  key  (author_id) references  users(id) on delete cascade);

create table image (
id serial primary key ,
data bytea,
file_size bigint,
media_type text,
users_id bigint unique references users (id) on delete cascade,
ad_id bigint references ad (id) on delete cascade
);

