ALTER TABLE stores
    ADD COLUMN last_update TIMESTAMP(6) NOT NULL DEFAULT '0001-01-01 00:00:00';

ALTER TABLE stores
    ALTER COLUMN last_update DROP DEFAULT;
