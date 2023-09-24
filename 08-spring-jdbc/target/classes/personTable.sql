
drop table if exists person;

create table person (
	 id int primary key auto_increment,
	 name varchar(20) not null,
	 age int not null
);
