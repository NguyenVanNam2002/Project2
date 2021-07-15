CREATE DATABASE snack_shop;

USE snack_shop;




CREATE TABLE category(
	CategoryID INT AUTO_INCREMENT PRIMARY KEY,
	`NameC` CHAR(5)
)
 INSERT INTO category(`NameC`)
 VALUES('Drink');
  INSERT INTO category(`NameC`)
 VALUES('Food');
 SELECT * FROM category;
CREATE TABLE products(
	ProductID INT AUTO_INCREMENT PRIMARY KEY,
	`Name` VARCHAR(255) NOT NULL,
	Price int NOT NULL ,
	Properties VARCHAR(255) NOT NULL,
	ImgLink VARCHAR(255),
	CategoryID INT
);

SELECT * FROM products;
alter table products
add foreign key (CategoryID) references category(CategoryID);


CREATE TABLE feedback(
	FbID INT AUTO_INCREMENT PRIMARY KEY ,
	Content TEXT NOT NULL ,
	ProductID INT 
);
alter table feedback
add foreign key (ProductID) references products(ProductID);
SELECT * FROM feedback;
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

SELECT * FROM account_client;
SELECT * FROM products WHERE `Name` LIKE '%a%';

Create table order_detail(
	order_ID INT AUTO_INCREMENT ,
	ProductID int,
	Client_ID varchar(50) ,
	Total_price varchar(45),
	Quantity int,
	Indate datetime,
	Primary key (order_ID,ProductID,Client_ID)
);
Alter table order_detail
Add foreign key (Client_ID) references account_client(accounts);

Alter table order_detail
Add foreign key (ProductID) references products(ProductID);
SELECT * FROM order_detail;

INSERT INTO order_detail(ProductID, Client_ID , Total_price, Quantity , Indate)
VALUES (15,'Namoizoioi@gmail.com',"100000",10,"2021-06-04 16:08:25");