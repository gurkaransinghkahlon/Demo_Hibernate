show databases;

use gurkaran;
show tables;
select * from alien_table;
drop table alien;


select * from Student;
select * from laptop;
select * from laptop_Student;
select * from Student_laptop;


drop table laptop_Student;
drop table Student_laptop;
drop table Student;
drop table laptop;


create table alien(
	aid int NOT NULL,
    aname varchar(35),
    tech varchar(35),
    Primary key(aid)
);

insert into alien values(1,'GK','Java');
insert into alien values(2,'SK','Pyton');
insert into alien values(3,'RK','JavaScript');
insert into alien values(4,'NK','RESTAPI');


select * from alien;