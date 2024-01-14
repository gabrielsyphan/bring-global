CREATE TABLE IF NOT EXISTS auth_manager (
    id INT AUTO_INCREMENT PRIMARY KEY,
    oauth_token VARCHAR(255) NOT NULL,
    oauth_token_secret VARCHAR(255) NOT NULL,
    CONSTRAINT unique_oauth_token UNIQUE (oauth_token),
    CONSTRAINT unique_oauth_token_secret UNIQUE (oauth_token_secret)
);
