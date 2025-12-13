CREATE TABLE IF NOT EXISTS articles (
    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(500) NOT NULL,
    description TEXT,
    content TEXT NOT NULL,
    publish_date DATE NOT NULL
);


