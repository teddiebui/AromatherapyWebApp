
USE [aromatherapy_massage];
-- Create tale Role --
CREATE TABLE [Role] (
    [role_name] VARCHAR(50) PRIMARY KEY,
);

-- Create table PermissionGroup --
CREATE TABLE [PermissionGroup] (
    [permission_group_name] VARCHAR(50) PRIMARY KEY,
);

-- Create tale Permission --
CREATE TABLE [Permission] (
    [permission_name] VARCHAR(50) PRIMARY KEY,
	[permission_group_name] VARCHAR(50),
	CONSTRAINT FK_PermissionGroup FOREIGN KEY ([permission_group_name]) REFERENCES [PermissionGroup]([permission_group_name])

);

-- Create tale RolePermission --
CREATE TABLE [RolePermission] (
    [role_name] VARCHAR(50) NOT NULL,
    [permission_name] VARCHAR(50) NOT NULL,
    PRIMARY KEY ([role_name], [permission_name]),
    CONSTRAINT FK_ROLEPERMISSION_ROLE FOREIGN KEY ([role_name]) REFERENCES [Role]([role_name]) ON DELETE CASCADE,
    CONSTRAINT FK_ROLEPERMISSION_PERMISSION FOREIGN KEY ([permission_name]) REFERENCES [Permission]([permission_name]) ON DELETE CASCADE
);

-- Create Employee table
CREATE TABLE [Employee] (
    [employee_id] INT IDENTITY(1,1) PRIMARY KEY,
    [employee_name] NVARCHAR(100),
    [employee_title] NVARCHAR(100),
    [employee_info] NVARCHAR(MAX),
    [employee_img_src] NVARCHAR(255),
    [employee_username] NVARCHAR(16) NOT NULL UNIQUE,
    [employee_hashed_password] NVARCHAR(255),
	[employee_is_locked] BIT DEFAULT 0,
	[employee_role_name] VARCHAR(50),
	[employee_join_date] DATETIME DEFAULT GETDATE(),
	[employee_create_time] DATETIME DEFAULT GETDATE()
	CONSTRAINT FK_Employee_Role FOREIGN KEY ([employee_role_name]) REFERENCES [role]([role_name])
);

-- Create PostStatus table
CREATE TABLE [PostStatus] (
    [post_status_id] INT IDENTITY(1,1) PRIMARY KEY,
    [post_status_name] NVARCHAR(50) NOT NULL
);

-- Create Post table with foreign key to Employee and PostStatus
CREATE TABLE [Post] (
    [post_id] INT IDENTITY(1,1) PRIMARY KEY,
    [employee_id] INT,
    [post_status] INT,
    [post_last_update_employee] INT,
    [post_last_update_time] DATETIME DEFAULT GETDATE(),
    [post_title] NVARCHAR(255),
    [post_content] NVARCHAR(MAX),
    [post_slug] NVARCHAR(255),
    [post_intro_img_src] NVARCHAR(255),
    [post_excerpt] NVARCHAR(MAX) ,
    [post_excerpt_img_src] NVARCHAR(255),
    [post_create_time] DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Post_Employee FOREIGN KEY ([employee_id]) REFERENCES [Employee]([employee_id]),
    CONSTRAINT FK_Post_PostStatus FOREIGN KEY ([post_status]) REFERENCES [PostStatus]([post_status_id])
);

-- Create Tag table
CREATE TABLE [Tag] (
    [tag_id] INT IDENTITY(1,1) PRIMARY KEY,
    [tag_name] NVARCHAR(100) NOT NULL,
);

-- Create PostTag table with foreign keys to Post and Tag
CREATE TABLE [PostTag] (
    [post_tag_id] INT IDENTITY(1,1) PRIMARY KEY,
    [post_id] INT,
    [tag_id] INT,
    CONSTRAINT FK_PostTag_Post FOREIGN KEY ([post_id]) REFERENCES [Post]([post_id]),
    CONSTRAINT FK_PostTag_Tag FOREIGN KEY ([tag_id]) REFERENCES [Tag]([tag_id])
);

-- Create Service table with foreign key to Employee
CREATE TABLE [Service] (
    [service_id] INT IDENTITY(1,1) PRIMARY KEY,
    [employee_id] INT,
    [service_title] NVARCHAR(255),
    [service_info] NVARCHAR(MAX),
    [service_img_src] NVARCHAR(255),
    [service_price] DECIMAL(10, 2),
    [service_create_time] DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Service_Employee FOREIGN KEY ([employee_id]) REFERENCES [Employee]([employee_id])
);

-- Create Course table with foreign key to Employee
CREATE TABLE [Course] (
    [course_id] INT IDENTITY(1,1) PRIMARY KEY,
    [employee_id] INT,
    [course_title] NVARCHAR(255),
    [course_info] NVARCHAR(100),
    [course_content] NVARCHAR(MAX),
    [course_img_src] NVARCHAR(255),
    [course_price] DECIMAL(10, 2),
    [course_create_date] DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Course_Employee FOREIGN KEY ([employee_id]) REFERENCES [Employee]([employee_id])
);

-- Create LoginSession table with foreign key to Employee (using employee_username)
CREATE TABLE [LoginHistory] (
    [id] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    [username] NVARCHAR(16) NOT NULL,
    [login_status] BIT NOT NULL,
    [login_device] NVARCHAR(255),
    [login_ip_address] NVARCHAR(255),
    [login_attempt] INT NOT NULL,
    [login_create_time] DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_LoginHistory_Employee FOREIGN KEY ([username]) REFERENCES [Employee]([employee_username])
);

