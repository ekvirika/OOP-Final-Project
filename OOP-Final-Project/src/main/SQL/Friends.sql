CREATE TABLE IF NOT EXISTS Friends (
    userId1 INT NOT NULL,
    userId2 INT NOT NULL,
    status ENUM('pending', 'accepted', 'rejected') NOT NULL DEFAULT 'pending',
    PRIMARY KEY (userId1, userId2),
    FOREIGN KEY (userId1) REFERENCES Accounts(userId) ON DELETE CASCADE,
    FOREIGN KEY (userId2) REFERENCES Accounts(userId) ON DELETE CASCADE,
    UNIQUE (userId1, userId2)
);