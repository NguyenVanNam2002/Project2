CREATE DATABASE snack_shop;

USE snack_shop;




CREATE TABLE category(
	CatID INT AUTO_INCREMENT PRIMARY KEY,
	Cat_name VARCHAR(255) NOT null
);

CREATE TABLE products(
	ProductID INT AUTO_INCREMENT PRIMARY KEY ,
	CatID INT NOT NULL ,
	Product_name VARCHAR(255) NOT NULL,
	Pice int NOT NULL ,
	Properties VARCHAR(255) NOT NULL ,
	image VARCHAR(255) NOT NULL

);
alter table products
add foreign KEY (CatID) references category(CatID);

CREATE TABLE feedback(
	FbID INT AUTO_INCREMENT PRIMARY KEY ,
	Content TEXT NOT NULL ,
	ProductID INT 
);
alter table feedback
add foreign key (ProductID) references products(ProductID);

CREATE TABLE account_admin(
	accountadmin VARCHAR(40) PRIMARY KEY ,
	passwordadmin VARCHAR(32) NOT NULL ,
	DoB date NOT NULL ,
	nameadmin VARCHAR(55) NOT null

);

INSERT INTO account_admin(accountadmin , passwordadmin,DoB , nameadmin)
VALUES ('Conzin@gmail.com','cb44f839a193bc118e6314e6dacb0da8',"1970-08-06",'Nguyen Van B');

SELECT * FROM account_admin;
CREATE TABLE account_client(
	accounts VARCHAR(40) PRIMARY KEY ,
	passwords VARCHAR(33) NOT NULL ,
	nick_name VARCHAR(55) NOT NULL ,
	phone VARCHAR(10) NOT NULL ,
	address VARCHAR(55) NOT null
);

DROP TABLE account_client;



SELECT * FROM account_client;
