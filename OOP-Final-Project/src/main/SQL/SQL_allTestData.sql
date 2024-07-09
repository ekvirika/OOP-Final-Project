# ----------------------------------------------------------------------------

-- Insert achievements
INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (1, 'Amateur Author', 'https://img.icons8.com/?size=100&id=PDVbwX9qe2CX&format=png&color=000000', 'The user created a quiz');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (2, 'Prolific Author', 'https://img.icons8.com/?size=100&id=68706&format=png&color=000000', 'The user created five quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (3, 'Prodigious Author', 'https://img.icons8.com/?size=100&id=yrPIS24okymU&format=png&color=000000', 'The user created ten quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (4, 'Quiz Machine', 'https://img.icons8.com/?size=100&id=114898&format=png&color=000000', 'The user took ten quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (5, 'I am the Greatest', 'https://img.icons8.com/?size=100&id=80747&format=png&color=000000', 'The user had the highest score on a quiz. Note, once earned this
achievement does not go away if someone else later bests the high score');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (6, 'Practice Makes Perfect', 'https://img.icons8.com/?size=100&id=114206&format=png&color=000000', 'The user took a quiz in practice mode.');


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('testUser', 'Sample Quiz', 'This is a sample quiz description.', 0, '[1, 2, 3, 4]', FALSE, FALSE, FALSE,
        CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 0, 'What is the capital of France?', 'Paris', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 5, 'Which of the following are programming languages?', NULL, NULL, '[\"Java\", \"Python\", \"HTML\"]',
        '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 6, 'Match the following pairs.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Red":"Color","Car":"Vehicle"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 5, 'Select all that apply.', NULL, NULL, '[\"Option 1\", \"Option 2\", \"Option 3\"]', '[0, 2]', NULL, NULL,
        NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('testUser', 'History Quiz', 'This quiz covers basic history questions.', 0, '[5, 6, 7, 8]', FALSE, FALSE, FALSE,
        CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 0, 'Who was the first President of the United States?', 'George Washington', NULL, NULL, NULL, NULL, NULL,
        NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 2, 'Which of the following events happened during World War II?', NULL, NULL,
        '[\"The moon landing\", \"Pearl Harbor attack\", \"Fall of the Berlin Wall\"]', '[1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 6, 'Match the following historical figures with their achievements.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Albert Einstein":"Theory of relativity","Isaac Newton":"Laws of motion"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 5, 'Select all that apply: Which of the following were ancient civilizations?', NULL, NULL,
        '[\"Maya\", \"Vikings\", \"Mesopotamia\"]', '[0, 2]', NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('testUser', 'Science Quiz', 'This quiz tests your knowledge on various science topics.', 0, '[9, 10, 11, 12]',
        FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 0, 'What is the chemical symbol for water?', 'H2O', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 5, 'Which of the following are elements on the periodic table?', NULL, NULL,
        '[\"Hydrogen\", \"Oxygen\", \"Water\"]', '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 6, 'Match the following scientists with their discoveries.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Marie Curie":"Radioactivity","Charles Darwin":"Theory of evolution"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 5, 'Select all that apply: Which of the following are planets in our solar system?', NULL, NULL,
        '[\"Earth\", \"Pluto\", \"Sirius\"]', '[0, 1]', NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('testUser', 'Literature Quiz', 'Test your knowledge on famous literature and authors.', 0, '[13, 14, 15, 16]',
        FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 0, 'Who wrote "1984"?', 'George Orwell', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 5, 'Which of the following are plays by William Shakespeare?', NULL, NULL,
        '[\"Hamlet\", \"Macbeth\", \"War and Peace\"]', '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 6, 'Match followings with their works.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Mark Twain":"The Adventures of Tom Sawyer","J.K. Rowling":"Harry Potter"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 5, 'Select all that apply: Which of the following are novels?', NULL, NULL,
        '[\"Moby Dick\", \"The Great Gatsby\", \"Leaves of Grass\"]', '[0, 1]', NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('FIFA', 'Sports Quiz', 'Test your knowledge on famous sports and athletes.', 0, '[17, 18, 19, 20]', FALSE,
        FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Who won the FIFA World Cup in 2022?', 'France', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Who holds the record for the most home runs in a single MLB season?', 'Barry Bonds', NULL, NULL, NULL,
        NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Who has won the most Grand Slam titles in tennis?', 'Margaret Court', NULL, NULL, NULL, NULL, NULL,
        NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Which country has won the most Olympic gold medals in the history of the Summer Games?', 'United States',
        NULL, NULL, NULL, NULL, NULL, NULL);



INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('KIKNA', 'ISE', 'GATESTE', 0, '[21, 22, 23, 24, 25, 26, 27]', FALSE, FALSE, TRUE, CURRENT_TIMESTAMP);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 0, 'Who won the FIFA World Cup in 2018?', 'France', NULL, NULL, NULL, NULL, NULL, NULL);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 1, 'Football G.O.A.T. is ______', 'Messi', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 2, 'Georgia-Portugal', NULL, NULL, ' [\" 2:0 \", \"  0:2 \", \"  3:1 \"] ', ' [0] ', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 3, 'Who is on the photo', 'Jondo', NULL, NULL, NULL,
        'https://physics.itmo.ru/sites/default/files/styles/seminar_speaker_full/public/speaker-photo/12244.jpg?itok=_XsYjE4D',
        NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 4, 'CHAMOTVALE 11-is JERADEBI ZRDADOBIT', NULL, NULL, NULL, NULL, NULL,
        ' [\" 11 \",\" 22 \", \" 33 \", \" 44 \", \" 55 \", \" 66 \", \" 77 \", \" 88 \", \" 99 \", \" 110 \"] ', NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 5, 'Please mark each statement below which is true', NULL, NULL, ' [\" a \", \" b \", \" c \"] ', ' [0, 2] ',
        NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6,6, 'Match the following authors with their works.', NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        '{"Mark Twain": "The Adventures of Tom Sawyer", "J.K. Rowling": "Harry Potter"}');





# ------------------------- new quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('alo', 'New Quiz', 'A new single-page quiz', 0, '[28, 29, 30, 31, 32, 33, 34]', TRUE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Question 1: Single Question Answer
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 0, 'What is the capital of France?', 'Paris', NULL, NULL, NULL, NULL, NULL, NULL);

-- Question 2: Fill-in-the-Blank
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 1, 'The chemical symbol for water is ______', 'H2O', NULL, NULL, NULL, NULL, NULL, NULL);

-- Question 3: Multiple Choice Single Answer
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 2, 'Which planet is known as the Red Planet?', NULL, NULL, '["Earth", "Mars", "Jupiter"]', '[1]', NULL, NULL, NULL);

-- Question 4: Image Question
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 3, 'Identify the structure shown in the image', 'Eiffel Tower', NULL, NULL, NULL,
        'https://example.com/eiffel_tower.jpg', NULL, NULL);

-- Question 5: Multiple Answer Fields
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 4, 'List the first five prime numbers', NULL, NULL, NULL, NULL, NULL,
        '["2", "3", "5", "7", "11"]', NULL);

-- Question 6: Multiple Choice Multiple Answers
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 5, 'Select the colors in the flag of France', NULL, NULL, '["Red", "Green", "Blue", "White"]', '[0, 2, 3]', NULL, NULL, NULL);

-- Question 7: Matching Pairs
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 6, 'Match the following capitals with their countries.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Paris": "France", "Berlin": "Germany", "Madrid": "Spain"}');
