create database job4j_cinema;

CREATE TABLE account(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL UNIQUE,
    phone    VARCHAR NOT NULL UNIQUE);


CREATE TABLE ticket(
    row        INT NOT NULL,
    cell       INT NOT NULL,
    account_id INT REFERENCES account (id),
    constraint uniqueTicket unique (row, cell));