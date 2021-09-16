create table if not exists users
(
	id int auto_increment,
	name varchar(250) not null,
	login varchar(250) not null,
	password varchar(250) not null,
	typeOfUser varchar(250) not null,
	picture varchar(500) not null,
	constraint users_id_uindex
		unique (id)
);

alter table users
	add primary key (id);

create table if not exists books
(
	id int auto_increment,
	name varchar(250) not null,
	author varchar(250) not null,
	rating double not null,
	price double not null,
	reserved tinyint(1) null,
	user_id int not null,
	constraint books_id_uindex
		unique (id),
	constraint books_users_id_fk
		foreign key (user_id) references users (id)
);

alter table books
	add primary key (id);

create table if not exists comments
(
	id int auto_increment,
	time datetime not null,
	user_id int not null,
	description varchar(500) not null,
	book_id int not null,
	constraint users_id_uindex
		unique (id),
	constraint comments_books_id_fk
		foreign key (book_id) references books (id),
	constraint comments_users_id_fk
		foreign key (user_id) references users (id)
);

alter table comments
	add primary key (id);

create table if not exists likes
(
	id int auto_increment,
	user_id int not null,
	book_id int not null,
	constraint likes_like_id_uindex
		unique (id),
	constraint likes_books_id_fk
		foreign key (book_id) references books (id),
	constraint likes_users_id_fk
		foreign key (user_id) references users (id)
);

alter table likes
	add primary key (id);

