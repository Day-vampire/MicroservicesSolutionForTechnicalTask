create table users
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  TEXT NOT NULL,
    last_name   TEXT NOT NULL,
    middle_name TEXT NOT NULL
);

create table accounts
(
    id      BIGSERIAL PRIMARY KEY,
    status  TEXT NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

create table persons
(
    id       BIGSERIAL PRIMARY KEY,
    login    TEXT NOT NULL,
    password TEXT NOT NULL,
    role   TEXT NOT NULL
);

INSERT INTO users (first_name, last_name, middle_name)
VALUES ('Иван', 'Иванов', 'Иванович'),
       ('Петр', 'Петров', 'Петрович'),
       ('Сидор', 'Сидоров', 'Сидорович'),
       ('Анна', 'Антонова', 'Антоновна'),
       ('Анна', 'Иваконова', 'Антоновна'),
       ('Мария', 'Маркова', 'Марковна');

INSERT INTO accounts (status, user_id)
VALUES ('OPENED', 1),
       ('OPENED', 2),
       ('CLOSED', 3),
       ('OPENED', 5),
       ('CLOSED', 6),
       ('OPENED', 1),
       ('OPENED', 3),
       ('CLOSED', 4);