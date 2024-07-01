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


CREATE TABLE Quiz (
                      quizID INT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(255) NOT NULL,
                      quizName VARCHAR(255) NOT NULL,
                      quizDescription TEXT,
                      quizScore INT DEFAULT 0,
                      questionIds TEXT,
                      isSinglePage BOOLEAN DEFAULT FALSE,
                      randomizeQuestions BOOLEAN DEFAULT FALSE,
                      immediateFeedback BOOLEAN DEFAULT FALSE,
                      createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      PRIMARY KEY (quizID)
);

-- Create Question table
CREATE TABLE Question (
                          questionId INT NOT NULL AUTO_INCREMENT,
                          quizId INT NOT NULL,
                          questionType INT NOT NULL,
                          questionText TEXT NOT NULL,
                          singleQuestionAnswer TEXT,
                          alternativeAnswers TEXT,
                          multipleChoiceAnswers TEXT,
                          multipleChoiceCorrectIndexes TEXT,
                          questionImage TEXT,
                          multipleAnswerFields TEXT,
                          matchingPairs TEXT,
                          PRIMARY KEY (questionId),
                          FOREIGN KEY (quizId) REFERENCES Quiz(quizID)
);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions, immediateFeedback, createTime)
VALUES ('testUser', 'History Quiz', 'This quiz covers basic history questions.', 0, '[5, 6, 7, 8]', FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (2, 1, 'Who was the first President of the United States?', 'George Washington', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (2, 2, 'Which of the following events happened during World War II?', NULL, NULL, '[\"The moon landing\", \"Pearl Harbor attack\", \"Fall of the Berlin Wall\"]', '[1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (2, 3, 'Match the following historical figures with their achievements.', NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"Albert Einstein\",\"value\":\"Theory of Relativity\"},{\"key\":\"Isaac Newton\",\"value\":\"Laws of Motion\"}]');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (2, 4, 'Select all that apply: Which of the following were ancient civilizations?', NULL, NULL, '[\"Maya\", \"Vikings\", \"Mesopotamia\"]', '[0, 2]', NULL, NULL, NULL);



-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions, immediateFeedback, createTime)
VALUES ('testUser', 'Science Quiz', 'This quiz tests your knowledge on various science topics.', 0, '[9, 10, 11, 12]', FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (3, 1, 'What is the chemical symbol for water?', 'H2O', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (3, 2, 'Which of the following are elements on the periodic table?', NULL, NULL, '[\"Hydrogen\", \"Oxygen\", \"Water\"]', '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (3, 3, 'Match the following scientists with their discoveries.', NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"Marie Curie\",\"value\":\"Radioactivity\"},{\"key\":\"Charles Darwin\",\"value\":\"Theory of Evolution\"}]');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (3, 4, 'Select all that apply: Which of the following are planets in our solar system?', NULL, NULL, '[\"Earth\", \"Pluto\", \"Sirius\"]', '[0, 1]', NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions, immediateFeedback, createTime)
VALUES ('testUser', 'Literature Quiz', 'Test your knowledge on famous literature and authors.', 0, '[13, 14, 15, 16]', FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (4, 1, 'Who wrote "1984"?', 'George Orwell', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (4, 2, 'Which of the following are plays by William Shakespeare?', NULL, NULL, '[\"Hamlet\", \"Macbeth\", \"War and Peace\"]', '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (4, 3, 'Match the following authors with their works.', NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"Mark Twain\",\"value\":\"The Adventures of Tom Sawyer\"},{\"key\":\"J.K. Rowling\",\"value\":\"Harry Potter\"}]');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (4, 4, 'Select all that apply: Which of the following are novels?', NULL, NULL, '[\"Moby Dick\", \"The Great Gatsby\", \"Leaves of Grass\"]', '[0, 1]', NULL, NULL, NULL);

