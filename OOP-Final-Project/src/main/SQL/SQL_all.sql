-- Create the OOP_QUIZ database
CREATE DATABASE IF NOT EXISTS OOP_QUIZ;

-- Use the OOP_QUIZ database
USE OOP_QUIZ;

-- Create the Accounts table
CREATE TABLE IF NOT EXISTS Accounts (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL UNIQUE,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    password VARCHAR(255),
    email VARCHAR(100) UNIQUE,
    imageUrl VARCHAR(255),
    salt VARCHAR(10),
    CONSTRAINT chk_password_length CHECK (CHAR_LENGTH(password) >= 8)
);

-- Create the Friends table
CREATE TABLE IF NOT EXISTS Friends (
    userId1 INT NOT NULL,
    userId2 INT NOT NULL,
    status ENUM('pending', 'accepted', 'rejected') NOT NULL DEFAULT 'pending',
    PRIMARY KEY (userId1, userId2),
    FOREIGN KEY (userId1) REFERENCES Accounts(userId) ON DELETE CASCADE,
    FOREIGN KEY (userId2) REFERENCES Accounts(userId) ON DELETE CASCADE,
    UNIQUE (userId1, userId2)
);

-- Create the Achievements table
CREATE TABLE IF NOT EXISTS Achievements (
    achievementId INT AUTO_INCREMENT PRIMARY KEY,
    achievementName VARCHAR(255) NOT NULL,
    achievementUrl VARCHAR(255),
    achievementDescription TEXT
);

-- Create the Quizzes table
CREATE TABLE IF NOT EXISTS Quizzes (
    quizId INT AUTO_INCREMENT PRIMARY KEY,
    quizName VARCHAR(255) NOT NULL,
    description TEXT,
    createdBy INT,
    FOREIGN KEY (createdBy) REFERENCES Accounts(userId) ON DELETE SET NULL
);

-- Create the Questions table
CREATE TABLE IF NOT EXISTS Questions (
    questionId INT AUTO_INCREMENT PRIMARY KEY,
    quizId INT,
    questionType ENUM('Question-Response', 'Fill in the Blank', 'Multiple Choice', 'Picture-Response') NOT NULL,
    questionText TEXT NOT NULL,
    questionImageUrl VARCHAR(255),
    FOREIGN KEY (quizId) REFERENCES Quizzes(quizId) ON DELETE CASCADE
);

-- Create the UserAchievements table
CREATE TABLE IF NOT EXISTS UserAchievements (
    userId INT,
    achievementId INT,
    PRIMARY KEY (userId, achievementId),
    FOREIGN KEY (userId) REFERENCES Accounts(userId) ON DELETE CASCADE,
    FOREIGN KEY (achievementId) REFERENCES Achievements(achievementId) ON DELETE CASCADE
);

-- Create the UserQuizzes table
CREATE TABLE IF NOT EXISTS UserQuizzes (
    userId INT,
    quizId INT,
    score INT,
    PRIMARY KEY (userId, quizId),
    FOREIGN KEY (userId) REFERENCES Accounts(userId) ON DELETE CASCADE,
    FOREIGN KEY (quizId) REFERENCES Quizzes(quizId) ON DELETE CASCADE
);

-- Insert sample data into Accounts table
INSERT INTO Accounts (userName, firstName, lastName, password, email, imageUrl, salt) VALUES
('john_doe', 'John', 'Doe', 'password123', 'john.doe@example.com', NULL, 'salt123'),
('jane_smith', 'Jane', 'Smith', 'password123', 'jane.smith@example.com', NULL, 'salt123');

-- Insert sample data into Achievements table
INSERT INTO Achievements (achievementName, achievementUrl, achievementDescription) VALUES
('Quiz Master', 'http://example.com/quiz-master', 'Complete 20 quizzes'),
('Speed Demon', 'http://example.com/speed-demon', 'Complete a quiz in under 5 minutes');

-- Insert sample data into Quizzes table
INSERT INTO Quizzes (quizName, description, createdBy) VALUES
('History Quiz', 'A quiz about historical events', 1),
('Science Quiz', 'A quiz about scientific facts', 2);

-- Insert sample data into Questions table
INSERT INTO Questions (quizId, questionType, questionText, questionImageUrl) VALUES
(1, 'Question-Response', 'Who was the first President of the United States?', NULL),
(1, 'Multiple Choice', 'Which year did the WW1 start?', NULL),
(2, 'Fill in the Blank', 'The chemical symbol for water is ___.', NULL),
(2, 'Picture-Response', 'Identify the structure in the image.', 'http://example.com/image.jpg');

-- Insert sample data into UserAchievements table
INSERT INTO UserAchievements (userId, achievementId) VALUES
(1, 1),
(2, 2);

-- Insert sample data into UserQuizzes table
INSERT INTO UserQuizzes (userId, quizId, score) VALUES
(1, 1, 90),
(2, 2, 85);
