USE [authenticationService];

-- Disable foreign key checks
ALTER TABLE LoginHistory DROP CONSTRAINT FK_LoginHistory_Account;

-- Truncate tables
TRUNCATE TABLE LoginHistory;
TRUNCATE TABLE Account;

-- Re-enable foreign key checks
ALTER TABLE LoginHistory ADD CONSTRAINT FK_LoginHistory_Account FOREIGN KEY (username) REFERENCES Account(username);


-- Drop foreign key constraint first
ALTER TABLE LoginHistory DROP CONSTRAINT FK_LoginHistory_Account;

-- Drop tables
DROP TABLE LoginHistory;
DROP TABLE Account;
