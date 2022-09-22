create database recording_artists;

use recording_artists;

create table artist (
artist_id int primary key auto_increment,
artist_name varchar(100) not null
);

create table album (
album_id int primary key auto_increment,
album_title varchar(100) not null,
release_date date not null,
label varchar(50) not null
);

create table tracks (
song_id int primary key auto_increment,
song_title varchar(100) not null,
song_length varchar(5) not null
);

create table artist_album (
artist_id int not null,
album_id int not null,
constraint pk_artist_album
	primary key(artist_id, album_id)
    

);


