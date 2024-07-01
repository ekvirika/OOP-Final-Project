CREATE TABLE Quiz (
    quizID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    quizName VARCHAR(255) NOT NULL,
    quizDescription TEXT,
    quizScore INT DEFAULT 0,
    isSinglePage BOOLEAN DEFAULT FALSE,
    randomizeQuestions BOOLEAN DEFAULT FALSE,
    immediateFeedback BOOLEAN DEFAULT FALSE,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (quizID)
);
