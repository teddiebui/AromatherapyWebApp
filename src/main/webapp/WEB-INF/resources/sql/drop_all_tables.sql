USE [auromatherapy_massage];

-- Drop foreign key constraints
ALTER TABLE [PostTag] DROP CONSTRAINT FK_PostTag_Post;
ALTER TABLE [PostTag] DROP CONSTRAINT FK_PostTag_Tag;
ALTER TABLE [Post] DROP CONSTRAINT FK_Post_Employee;
ALTER TABLE [Post] DROP CONSTRAINT FK_Post_PostStatus;
ALTER TABLE [Service] DROP CONSTRAINT FK_Service_Employee;
ALTER TABLE [Course] DROP CONSTRAINT FK_Course_Employee;

-- Drop tables
DROP TABLE [PostTag];
DROP TABLE [Post];
DROP TABLE [Tag];
DROP TABLE [Service];
DROP TABLE [Course];
DROP TABLE [PostStatus];
DROP TABLE [Employee];
