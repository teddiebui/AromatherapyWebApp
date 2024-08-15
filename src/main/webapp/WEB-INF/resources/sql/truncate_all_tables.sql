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

-- Truncate tables
TRUNCATE TABLE [PermissionGroup];
TRUNCATE TABLE [Permission];
TRUNCATE TABLE [RolePermission];
TRUNCATE TABLE [Role];
TRUNCATE TABLE [PostTag];
TRUNCATE TABLE [Post];
TRUNCATE TABLE [Tag];
TRUNCATE TABLE [Service];
TRUNCATE TABLE [Course];
TRUNCATE TABLE [PostStatus];
TRUNCATE TABLE [Employee];
TRUNCATE TABLE [LoginHistory];

-- Re-add foreign key constraints
ALTER TABLE [Permission] ADD CONSTRAINT FK_PermissionGroup FOREIGN KEY ([permission_group_name]) REFERENCES [PermissionGroup]([permission_group_name]);
ALTER TABLE [RolePermission] ADD CONSTRAINT FK_ROLEPERMISSION_ROLE FOREIGN KEY ([role_name]) REFERENCES [Role]([role_name]) ON DELETE CASCADE;
ALTER TABLE [RolePermission] ADD CONSTRAINT FK_ROLEPERMISSION_PERMISSION FOREIGN KEY ([permission_name]) REFERENCES [Permission]([permission_name]) ON DELETE CASCADE;
ALTER TABLE [Employee] ADD CONSTRAINT FK_Employee_Role FOREIGN KEY ([employee_id]) REFERENCES [Employee]([employee_id]);
ALTER TABLE [PostTag] ADD CONSTRAINT FK_PostTag_Post FOREIGN KEY ([post_id]) REFERENCES [Post]([post_id]);
ALTER TABLE [PostTag] ADD CONSTRAINT FK_PostTag_Tag FOREIGN KEY ([tag_id]) REFERENCES [Tag]([tag_id]);
ALTER TABLE [Post] ADD CONSTRAINT FK_Post_Employee FOREIGN KEY ([employee_id]) REFERENCES [Employee]([employee_id]);
ALTER TABLE [Post] ADD CONSTRAINT FK_Post_PostStatus FOREIGN KEY ([post_status]) REFERENCES [PostStatus]([post_status_id]);
ALTER TABLE [Service] ADD CONSTRAINT FK_Service_Employee FOREIGN KEY ([employee_id]) REFERENCES [Employee]([employee_id]);
ALTER TABLE [Course] ADD CONSTRAINT FK_Course_Employee FOREIGN KEY ([employee_id]) REFERENCES [Employee]([employee_id]);
ALTER TABLE [LoginHistory] ADD CONSTRAINT FK_LoginHistory_Employee FOREIGN KEY ([username]) REFERENCES [Employee]([employee_username]);
