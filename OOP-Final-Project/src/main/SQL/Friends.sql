CREATE TABLE IF NOT EXISTS Friends (
    userName1 VARCHAR(255) NOT NULL,
    userName2 VARCHAR(255) NOT NULL,
    status ENUM('pending', 'accepted', 'rejected') NOT NULL DEFAULT 'pending',
    PRIMARY KEY (userName1, userName2),
    FOREIGN KEY (userName1) REFERENCES Accounts(userName) ON DELETE CASCADE,
    FOREIGN KEY (userName2) REFERENCES Accounts(userName) ON DELETE CASCADE,
    UNIQUE (userName1, userName2)
);