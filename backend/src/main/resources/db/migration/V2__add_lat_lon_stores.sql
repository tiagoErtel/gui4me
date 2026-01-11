ALTER TABLE stores
    ADD COLUMN latitude DOUBLE PRECISION,
    ADD COLUMN longitude DOUBLE PRECISION;

UPDATE stores SET
    latitude = 0,
    longitude = 0;
