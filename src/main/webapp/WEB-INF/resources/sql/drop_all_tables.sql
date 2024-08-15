USE [aromatherapy_massage];

-- Drop foreign key constraints
ALTER TABLE [Permission] DROP CONSTRAINT FK_PERMISSIONGROUP;
ALTER TABLE [RolePermission] DROP CONSTRAINT FK_ROLEPERMISSION_ROLE;
ALTER TABLE [RolePermission] DROP CONSTRAINT FK_ROLEPERMISSION_PERMISSION;
ALTER TABLE [Employee] DROP CONSTRAINT FK_Employee_Role;
ALTER TABLE [PostTag] DROP CONSTRAINT FK_PostTag_Post;
ALTER TABLE [PostTag] DROP CONSTRAINT FK_PostTag_Tag;
ALTER TABLE [Post] DROP CONSTRAINT FK_Post_Employee;
ALTER TABLE [Post] DROP CONSTRAINT FK_Post_PostStatus;
ALTER TABLE [Service] DROP CONSTRAINT FK_Service_Employee;
ALTER TABLE [Course] DROP CONSTRAINT FK_Course_Employee;
ALTER TABLE [LoginHistory] DROP CONSTRAINT FK_LoginHistory_Employee;

-- Drop tables
DROP TABLE [PermissionGroup];
DROP TABLE [Permission];
DROP TABLE [Role];
DROP TABLE [RolePermission];
DROP TABLE [PostTag];
DROP TABLE [Post];
DROP TABLE [Tag];
DROP TABLE [Service];
DROP TABLE [Course];
DROP TABLE [PostStatus];
DROP TABLE [Employee];
DROP TABLE [LoginHistory];
