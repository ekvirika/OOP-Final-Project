CREATE TABLE QuizHistory (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             quizId INT NOT NULL,
                             username VARCHAR(255) NOT NULL,
                             quizScore INT DEFAULT 0,
                             startTime TIME,
                             endTime TIME,
                             elapsedTime BIGINT DEFAULT 0,
                             FOREIGN KEY (quizId) REFERENCES Quiz(quizId) ON DELETE CASCADE,
                             FOREIGN KEY (username) REFERENCES Accounts(username) ON DELETE CASCADE
);
