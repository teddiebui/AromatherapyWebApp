USE [authenticationService];

INSERT INTO [Account] ([username], [employee_id], [hashed_password], [is_locked]) 
VALUES 
('jdoe', 1, '$2a$10$HjrS8X./HzT2FImIY6Rrz.jqnOiqz03L7JpEy2T7STxiqvMZ.Uw72', 0),
('jsmith', 2, '$2a$10$HjrS8X./HzT2FImIY6Rrz.jqnOiqz03L7JpEy2T7STxiqvMZ.Uw72', 0),
('rbrown', 3, '$2a$10$HjrS8X./HzT2FImIY6Rrz.jqnOiqz03L7JpEy2T7STxiqvMZ.Uw72', 0),
('ewhite', 4, '$2a$10$HjrS8X./HzT2FImIY6Rrz.jqnOiqz03L7JpEy2T7STxiqvMZ.Uw72', 1);



INSERT INTO [LoginHistory] (username, login_status, login_device, login_ip_address, login_attempt, login_create_time)
VALUES 
('jdoe', 1, 'Desktop', '192.168.1.1', 1, '2024-06-01 10:00:00'),
('jdoe', 1, 'Mobile', '192.168.1.2', 1, '2024-06-01 10:00:04'),
('jdoe', 0, 'Tablet', '192.168.1.3', 2, '2024-06-03 10:00:00'),
('jsmith', 1, 'Desktop', '192.168.1.4', 1, '2024-06-07 10:00:00'),
('jsmith', 0, 'Mobile', '192.168.1.5', 1, '2024-06-09 10:00:00'),
('rbrown', 1, 'Desktop', '192.168.1.6', 1, '2024-06-11 10:00:00'),
('rbrown', 1, 'Mobile', '192.168.1.7', 1, '2024-06-15 10:00:00'),
('jsmith', 0, 'Mobile', '192.168.1.5', 2, '2024-06-16 10:00:00'),
('rbrown', 1, 'Desktop', '192.168.1.6', 1.0, '2024-06-17 10:00:00'),
('rbrown', 1, 'Mobile', '192.168.1.7', 3, '2024-06-18 10:00:00'),
('ewhite', 1, 'Mobile', '192.168.1.7', 4, '2024-06-19 10:00:00')

INSERT INTO [LoginHistory] (username, login_status, login_device, login_ip_address, login_attempt, refresh_key, [is_refresh_key_active], [login_create_time])
VALUES 
('jdoe', 1, 'Desktop', '255.255.255.255', 0, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJqZG9lIiwiZXhwIjoxNzI0NTMyNTk2LCJpYXQiOjE3MjQ0ODkzOTZ9.e_iItvcTk62WFQibX794mEDohf5wd9d9wjaU_Fkf03jc_xJIA6WtXRSsK64n8YW7k4cLC2sfiw8qwEQeBnkTUgFyb12TBz_6jUwAI7AmP8RlEjYBQU8-hHaGR0W8pw4XCmCwrxqlyQkzVA2qYJnDOJFNvgpXco_ziGk027f74NMr2Yfepjp6RN4GjlHQTnX7Fj___M6jnJhqRr0F5d0Ss6s3IZgXbXVN43CM375Az6_WHPadnWrhxSUVVs4WAVJAMafnBWscD7PuBZnzFV84Ifnias74wQ8ztAtNjqy-mq-gxgzx62lUdG7JxKPLgt_WGwDfJIe7e0NnQVCMQw', 0, '2024-06-20 10:00:00'),
('jdoe', 1, 'Desktop', '255.255.255.255', 0, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJqZG9lIiwiZXhwIjoxNzI0NDQ2MTgzLCJpYXQiOjE3MjQ0MDI5ODN9.UhiOoIONQ1JgRNzREcOJuqakhCNiKVffND8r2FOqZPYXZ2g1x-2ckQL3AaxHmev3ZAZ2ETW8k1fW0cn42KCfqTFiLVekkiJ9l2CW5846gurBUs7huYNFNIk_1ipyduYtLGm3SPmDvmrKSvIGXIOEBOdB6UMbRd4sgJG4OAzLgvjooQlJJ63TlQCWrEd_PL-pgPrRluwLfLRltuskP44Wxn9QUEbXhEvFmn6sd1NpvYa8JMuHvGBLC3C1yJI-awMogH50mXvLO_znv5nkVBnNXZ2HC5ICTgLwi5c76Q8dnn-uD9UCJG7Rwd9i7osAfor4D2KeLSwkio4UoDnIewGmbVTvRyz34AibvI4e87QpHbQ3vX1J8iy7zclwyNJNPMbzTWSZunu59OfVbLWymPqeIg-1v1h5uGys5yglfj6zIemw1YDnUpb_8r4yp-5yTKex8smyqao53Jl3vzPpU11qPVPk0kGdRPsH72cXOKIZM042tDip2j6AMGqapDZNFFxrLqJae6iMwJ3UItGLX7T6Ml2aXidt3cf_vlz5d_VRenec1vfkuoVcjuSCNde_40zGgrAZfrycxf4X0t5PolEh7usv5E38xeCAv2qUM1ObSXYcftR5Lh-QYfzS62-iFSfEYFNgQU6YTq9l8GawaOwNBhwZ5IDnOsmKDuc', 1, '2024-06-21 10:00:00')

