CREATE DATABASE IF NOT EXISTS QuizSystem;

USE QuizSystem;

CREATE TABLE Users (
    usrID int not null auto_increment,
    usrType varchar(1) not null,
    email varchar(255) not null,
    fName varchar(50),
    lName varchar(50),
    password text not null,
    salt text not null,
    course varchar(30),
    PRIMARY KEY (usrID),
    UNIQUE INDEX index_users_email (email)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Quizzes (
    quizID int not null auto_increment,
    usrID int not null,
    timeLimit decimal(7,2),
    topic text,
    name varchar(255),
    PRIMARY KEY (quizID),
    CONSTRAINT fk_quizzes_users FOREIGN KEY (usrID) REFERENCES Users(usrID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE QuizCompletions (
    quizID int not null auto_increment,
    usrID int not null,
    PRIMARY KEY (quizID, usrID),
    CONSTRAINT fk_quizcompletions_users FOREIGN KEY (usrID) REFERENCES Users(usrID),
    CONSTRAINT fk_quizcompletions_quizzes FOREIGN KEY (quizID) REFERENCES Quizzes(quizID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Questions (
    questionID int not null auto_increment,
    quizID int not null,
    usrID int not null,
    category varchar(50),
    title varchar(50),
    question text not null,
    PRIMARY KEY (questionID),
    CONSTRAINT fk_questions_users FOREIGN KEY (usrID) REFERENCES Users(usrID),
    CONSTRAINT fk_questions_quizzes FOREIGN KEY (quizID) REFERENCES Quizzes(quizID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE Answers (
    answerID int not null auto_increment,
    category varchar(50),
    questionID int not null,
    PRIMARY KEY (answerID),
    CONSTRAINT fk_answers_questions FOREIGN KEY (questionID) REFERENCES Questions(questionID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE AttemptAnswers (
    attemptAnswerID int not null auto_increment,
    quizID int not null,
    usrID int not null,
    questionID int not null,
    answerID int not null,
    marks int,
    PRIMARY KEY (attemptAnswerID),
    CONSTRAINT fk_attemptanswers_quizzes FOREIGN KEY (quizID) REFERENCES Quizzes(quizID),
    CONSTRAINT fk_attemptanswers_users FOREIGN KEY (usrID) REFERENCES Users(usrID),
    CONSTRAINT fk_attemptanswers_questions FOREIGN KEY (questionID) REFERENCES Questions(questionID),
    CONSTRAINT fk_attemptanswers_answers FOREIGN KEY (answerID) REFERENCES Answers(answerID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
