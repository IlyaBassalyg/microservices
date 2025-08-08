CREATE SEQUENCE IF NOT EXISTS mp3_resource_id_seq START 1;

CREATE TABLE IF NOT EXISTS mp3_resource (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('mp3_resource_id_seq'),
    body BYTEA
);