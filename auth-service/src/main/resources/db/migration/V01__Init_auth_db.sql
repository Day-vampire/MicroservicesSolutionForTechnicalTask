create table persons
(
    id       BIGSERIAL PRIMARY KEY,
    login    TEXT NOT NULL,
    password TEXT NOT NULL,
    role   TEXT NOT NULL
);
