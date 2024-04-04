create table if not exists clients (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id VARCHAR(36),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    purchase VARCHAR(255)

    );