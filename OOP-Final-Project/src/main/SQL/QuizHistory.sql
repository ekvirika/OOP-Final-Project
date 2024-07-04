CREATE TABLE QuizHistory (
                             quizId INT NOT NULL,
                             username VARCHAR(255) NOT NULL,
                             quizScore INT DEFAULT 0,
                             startTime TIME,
                             endTime TIME,
                             elapsedTime BIGINT DEFAULT 0,
                             PRIMARY KEY (quizId, username),
                             FOREIGN KEY (quizId) REFERENCES Quiz(quizId),
                             FOREIGN KEY (username) REFERENCES User(username)
);
