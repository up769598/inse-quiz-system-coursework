CREATE DATABASE IF NOT EXISTS QuizSystem;

CREATE TABLE Users (
    usrID varchar(10) not null,
    usrType varchar(1) not null,
    fName varchar(50),
    lName varchar(50),
    password text not null,
    salt text not null,
    course varchar(30),
    PRIMARY KEY (usrID),
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE QuizCompletions (
    quizID int NOT NULL,
    usrID varchar(10) NOT NULL,
    PRIMARY KEY (quizID, usrID),
    FOREIGN KEY (usrID) REFERENCES Users(usrID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Quizzes (
    quizID int not null,
    usrID varchar(10) not null,
    PRIMARY KEY (quizID),
    FOREIGN KEY (usrID) REFERENCES Users(usrID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Questions (
    questionID int autoincrement,
    quizID int not null,
    category varchar(50),
    title varchar(50),
    question text not null,
    PRIMARY KEY (questionID),
    FOREIGN KEY (usrID) REFERENCES Users(usrID),
    FOREIGN KEY (quizID) REFERENCES Quizzes(quizID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Answers (
    answerID int autoincrement,
    category varchar(50),
    questionID int not null,
    PRIMARY KEY (answerID),
    FOREIGN KEY (questionID) REFERENCES Questions(questionID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

