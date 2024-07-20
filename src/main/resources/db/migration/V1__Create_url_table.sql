CREATE TABLE IF NOT EXISTS url  (
    id INTEGER PRIMARY KEY,
    url VARCHAR(500) NOT NULL,
    short_url VARCHAR(200),
    creation_date DATE NOT NULL
)