CREATE DATABASE Quiz;

CREATE TABLE Student(
	StudentID INT auto_increment PRIMARY KEY,
	userName VARCHAR(20),
	password VARCHAR(20),
	fName VARCHAR(20),
	lName VARCHAR(20),
	salt VARCHAR(20)
	);

CREATE TABLE Quizzes(
	quizID INT auto_increment PRIMARY KEY,
	quizName VARCHAR(20),
	quizSetBy VARCHAR(20),
	quizTopic VARCHAR(20)
	);

CREATE TABLE Questions(
	questionID INT auto_increment PRIMARY KEY,
	quizID INT,
	question VARCHAR(20),
	answers1 VARCHAR(20),
	answers2 VARCHAR(20),
	answers3 VARCHAR(20),
	answers4 VARCHAR(20),
	answers5 VARCHAR(20),
	CONSTRAINT fk01 FOREIGN KEY (quizID) REFERENCES Quizzes(quizID)
	);

CREATE TABLE ANSWERS(
	studentID INT, 
	questionID INT,
	correctAnswer VARCHAR(20),
	CONSTRAINT CK01 PRIMARY KEY (studentID,questionID)
	);







