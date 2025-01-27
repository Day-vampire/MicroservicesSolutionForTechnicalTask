
create table accounts
(
    id      BIGSERIAL PRIMARY KEY,
    status  TEXT NOT NULL,
    user_id BIGINT NOT NULL
);

create table persons
(
    id       BIGSERIAL PRIMARY KEY,
    login    TEXT NOT NULL,
    password TEXT NOT NULL,
    role   TEXT NOT NULL
);

INSERT INTO accounts (status, user_id)
VALUES ('OPENED', 1),
       ('OPENED', 2),
       ('CLOSED', 3),
       ('OPENED', 5),
       ('CLOSED', 6),
       ('OPENED', 1),
       ('OPENED', 3),
       ('CLOSED', 4);