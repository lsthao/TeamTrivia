CREATE TABLE teamtrivia.Category
(
    CategoryID int(11) PRIMARY KEY NOT NULL,
    CategoryName varchar(255) NOT NULL
);
INSERT INTO teamtrivia.Category (CategoryID, CategoryName) VALUES (1, 'Java');
CREATE TABLE teamtrivia.Difficulty
(
    DifficultyID int(11) PRIMARY KEY NOT NULL,
    DifficultyName varchar(255) NOT NULL
);
INSERT INTO teamtrivia.Difficulty (DifficultyID, DifficultyName) VALUES (1, 'Easy');
INSERT INTO teamtrivia.Difficulty (DifficultyID, DifficultyName) VALUES (2, 'Medium');
INSERT INTO teamtrivia.Difficulty (DifficultyID, DifficultyName) VALUES (3, 'Hard');
CREATE TABLE teamtrivia.Question
(
    QuestionID int(11) PRIMARY KEY NOT NULL,
    Question varchar(255) NOT NULL,
    Answer varchar(1000) NOT NULL,
    Difficulty_DifficultyID int(11) NOT NULL,
    Type_TypeID int(11) NOT NULL,
    Category_CategoryID int(11) NOT NULL,
    CONSTRAINT fk_Question_Difficulty FOREIGN KEY (Difficulty_DifficultyID) REFERENCES Difficulty (DifficultyID),
    CONSTRAINT fk_Question_Type1 FOREIGN KEY (Type_TypeID) REFERENCES Type (TypeID),
    CONSTRAINT fk_Question_Category1 FOREIGN KEY (Category_CategoryID) REFERENCES Category (CategoryID)
);
CREATE INDEX fk_Question_Category1_idx ON teamtrivia.Question (Category_CategoryID);
CREATE INDEX fk_Question_Difficulty_idx ON teamtrivia.Question (Difficulty_DifficultyID);
CREATE INDEX fk_Question_Type1_idx ON teamtrivia.Question (Type_TypeID);
INSERT INTO teamtrivia.Question (QuestionID, Question, Answer, Difficulty_DifficultyID, Type_TypeID, Category_CategoryID) VALUES (1, 'What is Java?', 'A programming language.', 1, 2, 1);
INSERT INTO teamtrivia.Question (QuestionID, Question, Answer, Difficulty_DifficultyID, Type_TypeID, Category_CategoryID) VALUES (2, 'Java is difficult to use.', 'False', 1, 1, 1);
CREATE TABLE teamtrivia.Type
(
    TypeID int(11) PRIMARY KEY NOT NULL,
    TypeName varchar(255) NOT NULL
);
INSERT INTO teamtrivia.Type (TypeID, TypeName) VALUES (1, 'T/F');
INSERT INTO teamtrivia.Type (TypeID, TypeName) VALUES (2, 'Short Answer');