ALTER TABLE photo
    ADD COLUMN user_id bigint;

ALTER TABLE photo
    ADD CONSTRAINT FKbcys1buwxiujfybp4506v57w3
        FOREIGN KEY (user_id) REFERENCES users;

UPDATE photo
SET user_id = 1
WHERE id = 2;
