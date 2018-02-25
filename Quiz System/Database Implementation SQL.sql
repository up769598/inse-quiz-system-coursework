#author up813128
create database Quiz

--login 
-- create Table Lecturer (lectID int autoincrement, fName varchar(50), lName varchar(50), Course varchar(30));
-- Create Table Student(studID int autoincrement, );

 
 CREATE TABLE Users (
    usrID varchar(10) NOT NULL,
    usrType varchar(1) NOT NULL,
    fName varchar(50),
	lName varchar(50),
	password BLOB,
	course varchar(30),
    PRIMARY KEY (usrID),
);

 
 CREATE TABLE QuizCompletion (
	quizID int NOT NULL,
    usrID varchar(10) NOT NULL,
    usrType varchar(1) NOT NULL,
    fName varchar(50),
	lName varchar(50)
    PRIMARY KEY (quizID, usrID),
	FOREIGN KEY (usrID) REFERENCES Users(usrID)
);
 

 CREATE TABLE Quiz (
	quizID int NOT NULL,
    usrID varchar(10) NOT NULL,
    usrType varchar(1) NOT NULL,
    fName varchar(50),
	lName varchar(50)
    PRIMARY KEY (quizID, usrID),
	FOREIGN KEY (usrID) REFERENCES Users(usrID)
);

 CREATE TABLE Question (
	questionID int autoincrement,
	usrIDID Category varchar(50),
	Title varchar(50),
	Question BLOB
	FOREIGN KEY (usrID) REFERENCES Users(usrID)
);

 CREATE TABLE Answer (
	answerID int autoincrement,
	Category varchar(50)	
);

 
 --the user ID dose not need to be auto increment as we will use the student ID. set it to var char as if we
 --put lectures in they use names as ID also Password and salt needed to be added to the users table salt is varchar




