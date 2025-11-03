ALTER TABLE stores ADD COLUMN location geography(Point, 4326);

UPDATE stores
SET location = ST_SetSRID(ST_MakePoint(longitude, latitude), 4326)::geography;

CREATE INDEX stores_location_idx ON stores USING GIST (location);
