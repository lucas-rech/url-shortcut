CREATE TABLE IF NOT EXISTS url  (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(1500) NOT NULL,
    short_url VARCHAR(200),
    creation_date DATE NOT NULL
)