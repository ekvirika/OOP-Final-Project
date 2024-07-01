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

use oop_quiz;

-- Create Quiz table
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

-- Insert a new quiz
INSERT INTO Quiz (username, quizName, quizDescription, questionIds)
VALUES ('john_doe', 'Sample Quiz', 'This is a sample quiz description.', '');

-- Retrieve the quizID of the inserted quiz
SET @quizID = LAST_INSERT_ID();

-- Insert Question 1
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer)
VALUES (@quizID, 1, 'What is the capital of France?', 'Paris');

-- Retrieve question IDs and concatenate them into a single string
SET @questionIDs = (SELECT GROUP_CONCAT(questionId) FROM Question WHERE quizId = @quizID);

-- Update the quiz with the concatenated question IDs
UPDATE Quiz SET questionIds = @questionIDs WHERE quizID = @quizID;

