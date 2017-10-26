CREATE TABLE teamtrivia.Category
(
    CategoryID int(11) PRIMARY KEY NOT NULL,
    CategoryName varchar(255) NOT NULL
);
CREATE TABLE teamtrivia.Difficulty
(
    DifficultyID int(11) PRIMARY KEY NOT NULL,
    DifficultyName varchar(255) NOT NULL
);
CREATE TABLE teamtrivia.Question
(
    QuestionID int(11) PRIMARY KEY NOT NULL,
    Question varchar(255) NOT NULL,
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
CREATE TABLE teamtrivia.Type
(
    TypeID int(11) PRIMARY KEY NOT NULL,
    TypeName varchar(255) NOT NULL
);