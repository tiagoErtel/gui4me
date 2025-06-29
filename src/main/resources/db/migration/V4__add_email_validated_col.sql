ALTER TABLE users
    ADD COLUMN email_verified boolean;

UPDATE users SET email_verified = false;
