-- ============================================================
--  Script de prueba para PostgreSQL
-- ============================================================

DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(200) NOT NULL,
                       role VARCHAR(20) NOT NULL DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tasks (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       description TEXT,
                       done BOOLEAN DEFAULT FALSE,
                       deadline TIMESTAMP,
                       user_id BIGINT NOT NULL,
                       CONSTRAINT fk_user FOREIGN KEY (user_id)
                           REFERENCES users(id)
                           ON DELETE CASCADE
);

INSERT INTO users (username, email, password, role)
VALUES
    ('admin', 'admin@example.com', 'admin123', 'ADMIN'),
    ('daniel', 'daniel@example.com', 'daniel123', 'USER');

INSERT INTO tasks (title, description, done, deadline, user_id)
VALUES
    ('Terminar backend', 'Implementar API REST con Spring Boot', FALSE, '2025-11-10', 2),
    ('Diseñar frontend', 'Crear interfaz React con login y CRUD', FALSE, '2025-11-12', 2);
