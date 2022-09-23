drop database if exists rcttc;
create database rcttc;
use rcttc;

create table theater (
theater_id int primary key auto_increment,
theater_name varchar(100) not null,
address varchar(300) not null,
phone varchar(50) not null,
email varchar(250) not null
);

create table customer (
customer_id int primary key auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
customer_email varchar(250) not null,
customer_phone varchar(50),
customer_address varchar(100)
);

create table shows ( 
show_id int primary key auto_increment,
theater_id int not null,
title varchar(100) not null,
show_date date not null,
ticket_price decimal(8,2) not null,
	constraint fk_shows_theater
		foreign key (theater_id)
        references theater(theater_id)
);

create table ticket (
ticket_id int primary key auto_increment,
show_id int not null,
customer_id int not null,
seat varchar(2) not null,
	constraint fk_ticket_show
		foreign key (show_id)
        references shows(show_id),
    constraint fk_ticket_customer
		foreign key (customer_id)
        references customer(customer_id)
);
