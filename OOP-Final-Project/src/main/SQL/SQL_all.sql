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
    salt VARCHAR(16),
    CONSTRAINT chk_password_length CHECK (CHAR_LENGTH(password) >= 8)
);

# create friends table
CREATE TABLE IF NOT EXISTS Friends (
                                       userName1 VARCHAR(255) NOT NULL,
                                       userName2 VARCHAR(255) NOT NULL,
                                       status ENUM('pending', 'accepted', 'rejected') NOT NULL DEFAULT 'pending',
                                       PRIMARY KEY (userName1, userName2),
                                       FOREIGN KEY (userName1) REFERENCES Accounts(userName) ON DELETE CASCADE,
                                       FOREIGN KEY (userName2) REFERENCES Accounts(userName) ON DELETE CASCADE,
                                       UNIQUE (userName1, userName2)
);
#
# -- Create the Achievements table
# CREATE TABLE IF NOT EXISTS Achievements (
#     achievementId INT AUTO_INCREMENT PRIMARY KEY,
#     achievementName VARCHAR(255) NOT NULL,
#     achievementUrl VARCHAR(255),
#     achievementDescription TEXT
# );
#
# -- Create the Quizzes table
# CREATE TABLE IF NOT EXISTS Quizzes (
#     quizId INT AUTO_INCREMENT PRIMARY KEY,
#     quizName VARCHAR(255) NOT NULL,
#     description TEXT,
#     createdBy INT,
#     FOREIGN KEY (createdBy) REFERENCES Accounts(userId) ON DELETE SET NULL
# );
#
# -- Create the Questions table
# CREATE TABLE IF NOT EXISTS Questions (
#     questionId INT AUTO_INCREMENT PRIMARY KEY,
#     quizId INT,
#     questionType ENUM('Question-Response', 'Fill in the Blank', 'Multiple Choice', 'Picture-Response') NOT NULL,
#     questionText TEXT NOT NULL,
#     questionImageUrl VARCHAR(255),
#     FOREIGN KEY (quizId) REFERENCES Quizzes(quizId) ON DELETE CASCADE
# );
#
# -- Create the UserAchievements table
# CREATE TABLE IF NOT EXISTS UserAchievements (
#     userId INT,
#     achievementId INT,
#     PRIMARY KEY (userId, achievementId),
#     FOREIGN KEY (userId) REFERENCES Accounts(userId) ON DELETE CASCADE,
#     FOREIGN KEY (achievementId) REFERENCES Achievements(achievementId) ON DELETE CASCADE
# );
#
# -- Create the UserQuizzes table
# CREATE TABLE IF NOT EXISTS UserQuizzes (
#     userId INT,
#     quizId INT,
#     score INT,
#     PRIMARY KEY (userId, quizId),
#     FOREIGN KEY (userId) REFERENCES Accounts(userId) ON DELETE CASCADE,
#     FOREIGN KEY (quizId) REFERENCES Quizzes(quizId) ON DELETE CASCADE
# );
#


#     ___________________________________________________________
#      -------------------- FIll Tables ------------------------
#     _____________________________________________________________
# -- Insert sample data into Accounts table
# INSERT INTO Accounts (userName, firstName, lastName, password, email, imageUrl, salt) VALUES
# ('john_doe', 'John', 'Doe', 'password123', 'john.doe@example.com', NULL, 'salt123'),
# ('jane_smith', 'Jane', 'Smith', 'password123', 'jane.smith@example.com', NULL, 'salt123');
#
# -- Insert sample data into the Accounts table
# INSERT INTO Accounts (userName, firstName, lastName, password, email, imageUrl, salt)
# VALUES
#     ('johndoe', 'John', 'Doe', 'password123!', 'johndoe@example.com', 'https://example.com/johndoe.jpg', 'abcd1234'),
#     ('janesmith', 'Jane', 'Smith', 'strongpassword4', 'janesmith@example.com', 'https://example.com/janesmith.jpg', 'efgh5678'),
#     ('bobmiller', 'Bob', 'Miller', 'mypassword123', 'bobmiller@example.com', 'https://example.com/bobmiller.jpg', 'ijkl9012'),
#     ('sarahlee', 'Sarah', 'Lee', 'ilovecats7!', 'sarahlee@example.com', 'https://example.com/sarahlee.jpg', 'mnop3456'),
#     ('davidkim', 'David', 'Kim', 'password456$', 'davidkim@example.com', 'https://example.com/davidkim.jpg', 'qrst7890');
#
# -- Insert sample data into the Friends table
# INSERT INTO Friends (userName1, userName2, status)
# VALUES
#     ('johndoe', 'janesmith', 'accepted'),
#     ('johndoe', 'bobmiller', 'pending'),
#     ('janesmith', 'sarahlee', 'accepted'),
#     ('bobmiller', 'davidkim', 'rejected');
#
# -- Insert sample data into Achievements table
# INSERT INTO Achievements (achievementName, achievementUrl, achievementDescription) VALUES
# ('Quiz Master', 'http://example.com/quiz-master', 'Complete 20 quizzes'),
# ('Speed Demon', 'http://example.com/speed-demon', 'Complete a quiz in under 5 minutes');
#
# -- Insert sample data into Quizzes table
# INSERT INTO Quizzes (quizName, description, createdBy) VALUES
# ('History Quiz', 'A quiz about historical events', 1),
# ('Science Quiz', 'A quiz about scientific facts', 2);
#
# -- Insert sample data into Questions table
# INSERT INTO Questions (quizId, questionType, questionText, questionImageUrl) VALUES
# (1, 'Question-Response', 'Who was the first President of the United States?', NULL),
# (1, 'Multiple Choice', 'Which year did the WW1 start?', NULL),
# (2, 'Fill in the Blank', 'The chemical symbol for water is ___.', NULL),
# (2, 'Picture-Response', 'Identify the structure in the image.', 'http://example.com/image.jpg');
#
# -- Insert sample data into UserAchievements table
# INSERT INTO UserAchievements (userId, achievementId) VALUES
# (1, 1),
# (2, 2);
#
# -- Insert sample data into UserQuizzes table
# INSERT INTO UserQuizzes (userId, quizId, score) VALUES
# (1, 1, 90),
# (2, 2, 85);
