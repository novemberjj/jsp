create database jspdb;

use mysql;

create user 'jspuser'@'localhost' identified by 'mysql';

grant all privileges on jspdb.* to 'jspuser'@'localhost' with grant option;

flush privileges;

-- 2023-05-12
create table member(
id varchar(100) not null,
password varchar(100) not null,
email varchar(100) not null,
age int,
regdate datetime default now(),
last_login datetime default null,
primary key(id));

-- 2023-05-16
create table board(
bno int not null auto_increment,
title varchar(100) not null,
writer varchar(100) not null,
content text,
reg_date datetime default now(),
read_count int not null,
primary key(bno));

-- 2023-05-17
alter table board add read_count int default 0;
