-- Create the database
CREATE DATABASE auromatherapy_massage;
GO

-- Use the newly created database
USE auromatherapy_massage;
GO

-- Create the POST_STATUS table
CREATE TABLE POST_STATUS (
    post_status_id INT PRIMARY KEY NOT NULL,
    post_status_name NVARCHAR(10) NOT NULL
);
GO

-- Create the EMPLOYEE table
CREATE TABLE EMPLOYEE (
    employee_id INT PRIMARY KEY NOT NULL,
    employee_name NVARCHAR(50) NOT NULL,
    employee_title NVARCHAR(50) NOT NULL,
    employee_info NVARCHAR(1000) NOT NULL,
    employee_img_src NVARCHAR(200)
);
GO

-- Create the POST table
CREATE TABLE POST (
    post_id INT PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    post_status INT NOT NULL,
    post_last_update_employee INT,
    post_last_update_time DATETIME,
    post_title CHAR(50) NOT NULL,
    post_content VARCHAR(1000) NOT NULL,
    post_slug VARCHAR(50) NOT NULL,
    post_intro_img_src VARCHAR(200),
    post_excerpt VARCHAR(500),
    post_excerpt_img_src VARCHAR(200),
    post_create_time DATETIME,
    FOREIGN KEY (employee_id) REFERENCES EMPLOYEE(employee_id),
    FOREIGN KEY (post_status) REFERENCES POST_STATUS(post_status_id)
);
GO

-- Create the TAG table
CREATE TABLE TAG (
    tag_id INT PRIMARY KEY NOT NULL,
    tag_name VARCHAR(50) NOT NULL
);
GO

-- Create the POST_TAG table
CREATE TABLE POST_TAG (
    post_tag_id INT PRIMARY KEY NOT NULL,
    post_id INT NOT NULL,
    tag_id INT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES POST(post_id),
    FOREIGN KEY (tag_id) REFERENCES TAG(tag_id)
);
GO

-- Create the SERVICE table
CREATE TABLE SERVICE (
    service_id INT PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    service_title NVARCHAR(50) NOT NULL,
    service_info NVARCHAR(MAX) NOT NULL,
    service_img_src NVARCHAR(200),
    service_price FLOAT NOT NULL,
    service_create_time DATETIME NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES EMPLOYEE(employee_id)
);
GO

-- Create the COURSE table
CREATE TABLE COURSE (
    course_id INT PRIMARY KEY NOT NULL,
    employee_id INT NOT NULL,
    course_title NVARCHAR(50) NOT NULL,
    course_content NVARCHAR(1000) NOT NULL,
    course_img_src NVARCHAR(200),
    course_create_date DATETIME NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES EMPLOYEE(employee_id)
);
GO
