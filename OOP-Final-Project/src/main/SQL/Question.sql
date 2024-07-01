use oop_quiz;

drop table question;
drop table quiz;

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

# ------------------------------------------------
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
