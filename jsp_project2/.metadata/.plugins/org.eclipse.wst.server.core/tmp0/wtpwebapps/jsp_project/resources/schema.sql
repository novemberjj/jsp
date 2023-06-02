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

-- 2023-05-19
create table comment(
cno int not null auto_increment,
bno int default 0,
writer varchar(100) default "unknown",
content varchar(1000),
reg_date datetime default now(),
primary key(cno));

--2023-05-23
insert into board (title, writer, content) values('test','123','tt');

alter table board modify column read_count int not null default 0

select * from board limit 1,5; --5개씩 출력

select count(bno) from board where bno>0;

select * from board order by bno desc limit 0,10;

--2023-05-25
alter table board add column img_file text;