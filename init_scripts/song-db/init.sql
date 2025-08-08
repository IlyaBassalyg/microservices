CREATE TABLE IF NOT EXISTS song_metadata (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    artist VARCHAR(255),
    album VARCHAR(255),
    duration VARCHAR(20),
    year VARCHAR(4)
)