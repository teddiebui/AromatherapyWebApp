IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'authenticationService')
BEGIN
    CREATE DATABASE [authenticationService];
END
GO

-- Use the newly created database
USE [authenticationService];
GO

CREATE TABLE Account (
    username VARCHAR(16) NOT NULL PRIMARY KEY,
	employee_id int,
    hashed_password VARCHAR(255) NOT NULL,
    is_locked BIT DEFAULT 0,
    create_time DATETIME DEFAULT GETDATE()
);

CREATE TABLE LoginHistory (
    login_history_id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(16) NOT NULL,
    login_status BIT NOT NULL,
    login_device NVARCHAR(255),
    login_ip_address VARCHAR(15),
    login_attempt DECIMAL NOT NULL,
    refresh_key VARCHAR(MAX),
    is_refresh_key_active BIT DEFAULT 1,
    login_create_time DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_LoginHistory_Account FOREIGN KEY (username)
        REFERENCES Account(username)
);
