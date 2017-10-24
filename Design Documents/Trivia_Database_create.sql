-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-10-24 18:06:41.857

-- tables
-- Table: Answer
CREATE TABLE Answer (
    AnswerID int NOT NULL,
    Answer varchar(1000) NOT NULL,
    Correct bool NOT NULL,
    QuestionID int NOT NULL,
    CONSTRAINT Answer_pk PRIMARY KEY (AnswerID)
);

-- Table: Category
CREATE TABLE Category (
    CategoryID int NOT NULL,
    CategoryName varchar(255) NOT NULL,
    CONSTRAINT Category_pk PRIMARY KEY (CategoryID)
);

-- Table: Difficulty
CREATE TABLE Difficulty (
    DifficultyID int NOT NULL,
    DifficultyName varchar(255) NOT NULL,
    CONSTRAINT Difficulty_pk PRIMARY KEY (DifficultyID)
);

-- Table: Question
CREATE TABLE Question (
    QuestionID int NOT NULL,
    DifficultyID int NOT NULL,
    CategoryID int NOT NULL,
    TypeID int NOT NULL,
    Question varchar(255) NOT NULL,
    CONSTRAINT Question_pk PRIMARY KEY (QuestionID)
);

-- Table: Type
CREATE TABLE Type (
    TypeID int NOT NULL,
    TypeName varchar(255) NOT NULL,
    CONSTRAINT Type_pk PRIMARY KEY (TypeID)
);

-- foreign keys
-- Reference: Answer_Question (table: Answer)
ALTER TABLE Answer ADD CONSTRAINT Answer_Question FOREIGN KEY Answer_Question (QuestionID)
    REFERENCES Question (QuestionID);

-- Reference: Question_Category (table: Question)
ALTER TABLE Question ADD CONSTRAINT Question_Category FOREIGN KEY Question_Category (CategoryID)
    REFERENCES Category (CategoryID);

-- Reference: Question_Difficulty (table: Question)
ALTER TABLE Question ADD CONSTRAINT Question_Difficulty FOREIGN KEY Question_Difficulty (DifficultyID)
    REFERENCES Difficulty (DifficultyID);

-- Reference: Question_Type (table: Question)
ALTER TABLE Question ADD CONSTRAINT Question_Type FOREIGN KEY Question_Type (TypeID)
    REFERENCES Type (TypeID);

-- End of file.

