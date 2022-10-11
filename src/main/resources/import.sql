CREATE TABLE students (id bigserial primary key, name varchar(255));
INSERT INTO students VALUES ('Bob'), ('Bill'), ('John');

CREATE TABLE authors (id bigserial primary key, name varchar(255), surname varchar(255));
INSERT INTO authors VALUES ('Chuck', 'Palahniuk');
INSERT INTO authors VALUES ('Stephen', 'King');
INSERT INTO authors VALUES ('Erich-Maria', 'Remark');
INSERT INTO authors VALUES ('Bernard', 'Werber');
INSERT INTO authors VALUES ('Alexandre', 'Dumas');

CREATE TABLE books (id bigserial primary key, title varchar(255), author_id integer references authors(id));
INSERT INTO books VALUES ('Invisible Monsters', 1);
INSERT INTO books VALUES ('Under the Dome', 2);
INSERT INTO books VALUES ('Three comrades', 3);
INSERT INTO books VALUES ('The Thanatonauts', 4);
INSERT INTO books VALUES ('The Three Musketeerss', 4);