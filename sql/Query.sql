CREATE DATABASE snack_shop;

USE snack_shop;




CREATE TABLE category(
	ma_dm INT AUTO_INCREMENT PRIMARY KEY,
	ten_dm VARCHAR(255) NOT null
);

CREATE TABLE products(
	ma_sp INT AUTO_INCREMENT PRIMARY KEY ,
	ma_dm INT NOT NULL ,
	ten_sp VARCHAR(255) NOT NULL,
	gia_ca int NOT NULL ,
	thuoc_tinh VARCHAR(255) NOT NULL ,
	image VARCHAR(255) NOT NULL

);
alter table products
add foreign key (ma_dm) references category(ma_dm);

CREATE TABLE feedback(
	ma_fb INT AUTO_INCREMENT PRIMARY KEY ,
	noi_dung TEXT NOT NULL ,
	ma_sp INT 
);
alter table feedback
add foreign key (ma_sp) references products(ma_sp);

CREATE TABLE account_qtv(
	ten_tk VARCHAR(40) PRIMARY KEY ,
	mat_khau VARCHAR(25) NOT NULL ,
	DoB VARCHAR(10) NOT NULL ,
	ten_qtv VARCHAR(255) NOT null

);

CREATE TABLE account_client(
	ten_tai_khoan VARCHAR(40) PRIMARY KEY ,
	mat_khau VARCHAR(24) NOT NULL ,
	nick_name VARCHAR(255) NOT NULL ,
	phone VARCHAR(255) NOT NULL ,
	address VARCHAR(255) NOT null
);

CREATE TABLE older(
	ma_older INT AUTO_INCREMENT PRIMARY KEY ,
	ten_tai_khoan VARCHAR(40) NOT NULL ,
	ma_sp int , 
	gia_ca INT NOT NULL ,
	datetime_older VARCHAR(40) NOT null
);

alter table older
add foreign key (ten_tai_khoan) references account_client(ten_tai_khoan);


alter table older
add foreign key (ma_sp) REFERENCES products(ma_sp);

SELECT * FROM account_client;
