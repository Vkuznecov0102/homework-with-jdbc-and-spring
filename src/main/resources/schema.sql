drop table if exists user,email,pet;

create table user(
id bigint auto_increment primary key,
fio varchar(255),
email_id bigint,
pet_id bigint
);

create table email(
id bigint auto_increment primary key,
address varchar(255)
);

create table pet(
id bigint auto_increment primary key,
type varchar(255),
name varchar(255)
);

alter table user add foreign key(email_id) references email(id);

alter table user add foreign key(pet_id) references pet(id);