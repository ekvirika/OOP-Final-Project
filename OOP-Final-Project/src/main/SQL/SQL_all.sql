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
                                        achievementIds TEXT,
                                        quizIds TEXT,
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


CREATE TABLE Achievement (
                             achievementId INT NOT NULL AUTO_INCREMENT,
                             achievementName VARCHAR(255) NOT NULL,
                             achievementUrl VARCHAR(255),
                             achievementDescription TEXT,
                             PRIMARY KEY (achievementId)
);


CREATE TABLE QuizHistory (
                             quizId INT NOT NULL,
                             username VARCHAR(255) NOT NULL,
                             quizScore INT DEFAULT 0,
                             startTime TIME,
                             endTime TIME,
                             elapsedTime BIGINT DEFAULT 0,
                             PRIMARY KEY (quizId, username),
                             FOREIGN KEY (quizId) REFERENCES Quiz(quizId),
                             FOREIGN KEY (username) REFERENCES Accounts(username)
);


# ----------------------------------------------------------------------------
-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions, immediateFeedback, createTime)
VALUES ('testUser', 'Sample Quiz', 'This is a sample quiz description.', 0, '[1, 2, 3, 4]', FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (1, 1, 'What is the capital of France?', 'Paris', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (1, 2, 'Which of the following are programming languages?', NULL, NULL, '[\"Java\", \"Python\", \"HTML\"]', '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (1, 3, 'Match the following pairs.', NULL, NULL, NULL, NULL, NULL, NULL, '[{\"key\":\"Red\",\"value\":\"Color\"},{\"key\":\"Car\",\"value\":\"Vehicle\"}]');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (1, 4, 'Select all that apply.', NULL, NULL, '[\"Option 1\", \"Option 2\", \"Option 3\"]', '[0, 2]', NULL, NULL, NULL);




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


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions, immediateFeedback, createTime)
VALUES ('FIFA', 'Sports Quiz', 'Test your knowledge on famous sports and athletes.', 0, '[17, 18, 19, 20]', FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (5, 1, 'Who won the FIFA World Cup in 2018?', 'France', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (5, 1, 'Who holds the record for the most home runs in a single MLB season?', 'Barry Bonds', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (5, 1, 'Who has won the most Grand Slam titles in tennis?', 'Margaret Court', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, matchingPairs)
VALUES (5, 1, 'Which country has won the most Olympic gold medals in the history of the Summer Games?', 'United States', NULL, NULL, NULL, NULL, NULL, NULL);


