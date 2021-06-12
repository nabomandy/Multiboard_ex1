-- study1.sql
create table member (
id varchar(20) primary key, 
pass varchar(20), 
name varchar(20), 
gender number(1), 
tel varchar(20), 
email varchar(50), 
picture varchar(200));


create table board(
num int primary key,
boardid varchar(2) default '1',
name varchar(30),
pass varchar(20),
subject varchar(100),
content varchar(4000),
file1 varchar(100),
regdate date,
readcnt number(10),
ref int,
reflevel number(3),
refstep number(5));


create sequence boardseq;

create table multiboard(
num int primary key,
boardid varchar(2) default '1',
name varchar(30),
pass varchar(20),
subject varchar(100),
content varchar(4000),
file1 varchar(100),
regdate date,
readcnt number(10),
ref int,
reflevel number(3),
refstep number(5));





create sequence multiboardseq;




select * from ( select rownum rnum ,a.* 
from (select * from board order by ref desc , re_step) a )
where rnum between #{start} and #{end}




















create table board (
   num int primary key,  
   name varchar(30),
   pass varchar(20),
   subject varchar(100),
   content varchar(4000),
   file1 varchar(100),
   regdate date,
   readcnt number(10),
   ref int,
   reflevel number(3),
   refstep number(5));