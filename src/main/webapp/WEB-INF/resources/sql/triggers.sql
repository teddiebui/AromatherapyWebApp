CREATE TRIGGER lastUpdate
ON [Post]
AFTER UPDATE
AS
BEGIN
	UPDATE [Post]
	SET [post_last_update_time] = GETDATE()
	WHERE [post_id] IN (SELECT[post_id] FROM inserted);
END