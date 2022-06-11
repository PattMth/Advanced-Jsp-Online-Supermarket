/* Suppliers Table */
CREATE TABLE SUPPLIERS (
    SUPPLIERID INT NOT NULL PRIMARY KEY 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    "SUPPLIERNAME" VARCHAR (50),
    "COMPANYTYPE" VARCHAR (50),
    CONTACTNUMBER INT,
    "EMAIL" VARCHAR (50),
    "ADDRESS" VARCHAR (50),
    STATUS BOOLEAN
);


/* Create table for User_Account */
CREATE TABLE USER_ACCOUNT (
    "USERACCOUNTID" INT NOT NULL PRIMARY KEY
    GENERATED ALWAYS AS IDENTITY
    (START WITH 1, INCREMENT BY 1),
    USERNAME VARCHAR(100), 
    PASSWORD VARCHAR(120), 
    DATEOFBIRTH VARCHAR(10), 
    GENDER VARCHAR(20), 
    PROMOTIONALNEWSLETTER BOOLEAN, 
    REWARDPOINTS INTEGER
);


/* Create table for Access Log */
CREATE TABLE ACCESS_LOG (
    "ACCESSLOGID" INT NOT NULL PRIMARY KEY 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1), 
    USERACCOUNTID INTEGER, 
    "DATE" VARCHAR(20), 
    "TIME" VARCHAR(20),
    "ACTION" VARCHAR(20)
);


/* Create table for Customer */
CREATE TABLE "CUSTOMER" (
    "CUSTOMERID" INT NOT NULL PRIMARY KEY 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    "NAME" VARCHAR(50), 
    CONTACTNUMBER VARCHAR(12), 
    EMAIL VARCHAR(50), 
    BILLINGADDRESS VARCHAR(100), 
    ISREGISTERED BOOLEAN
);


/* Create table for Customer_User */
CREATE TABLE CUSTOMER_USER (
    USERACCOUNTID INTEGER NOT NULL,
    CUSTOMERID INTEGER NOT NULL,
    PRIMARY KEY(CUSTOMERID, USERACCOUNTID)
);


/* Create table for Staff */
CREATE TABLE STAFF (
    "STAFFID" INT NOT NULL PRIMARY KEY 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    "NAME" VARCHAR(50), EMAIL VARCHAR(50), 
    PHONENUMBER VARCHAR(12), 
    ADDRESS VARCHAR(100), 
    STAFFTYPE VARCHAR(20), 
    ACTIONHISTORY VARCHAR(100), 
    USERACCOUNTID INTEGER,
    FOREIGN KEY (USERACCOUNTID) REFERENCES USER_ACCOUNT
);


drop table DEVICE;
create table DEVICE (
    ID INT not null primary key 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    "NAME" VARCHAR(200) not null,
    "TYPE" VARCHAR(200),
    UNIT VARCHAR(100),
    PRICE DOUBLE default 0 not null,
    STOCK INTEGER default 0 not null
);

/*
DROP TABLE "ORDER";
CREATE TABLE "ORDER" (    
   "ID" INT not null primary key 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1000, INCREMENT BY 1),   
   "TOTAL" DOUBLE,     
   "DATE" TIMESTAMP,
   "STATUS" VARCHAR(50),
   "USERID" VARCHAR(50),
   "DEVICEID" INTEGER,
   "QUANTITY" INTEGER
);
*/

/* Order Dummy */
CREATE TABLE Orders (   
   orderID INT not null
    GENERATED ALWAYS AS IDENTITY
    (START WITH 1000, INCREMENT BY 1),
orderDescription VARCHAR(30) NOT NULL,
PRIMARY KEY (orderID)
);


/*Payment Table*/
CREATE TABLE Payment (
    paymentID INTEGER NOT NULL,
    paymentDate VARCHAR(20) NOT NULL,
    totalPrice INTEGER ,
    paymentType VARCHAR (20),
    paymentStatus VARCHAR (20),
    PRIMARY KEY (paymentID)
);


/*PaymentMethod table*/
CREATE TABLE Payment_Method_Details (
    methodID int NOT NULL 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    cardName VARCHAR(50),
    cardNumber INTEGER,
    cvc INTEGER ,
    bankName VARCHAR(50) ,
    userAccountID INTEGER,
    PRIMARY KEY (methodID),
    FOREIGN KEY (userAccountID) REFERENCES User_Account(userAccountID)
);


/* Shipment_Details Table */
CREATE TABLE Shipment_Details (
    shipmentDetailsID int NOT NULL 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    streetAddress varchar(50),
    suburb varchar(20),
    postcode int,
    state char(3),
    userAccountID int,
    PRIMARY KEY (shipmentDetailsID),
    FOREIGN KEY (userAccountID) REFERENCES User_Account(userAccountID)
);


/* Shipment Table */
CREATE Table Shipment (
    shipmentID int NOT NULL 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    courierName varchar (20),
    shipmentStatus varchar(20),
    trackingNumber varchar (20),
    shipmentDate varchar(10),
    shipmentDetailsID int,
    orderID int,
    PRIMARY KEY (shipmentID),
    FOREIGN KEY (shipmentDetailsID) REFERENCES Shipment_Details(shipmentDetailsID),
    FOREIGN KEY (orderID) REFERENCES Orders(orderID)
);
