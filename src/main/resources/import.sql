CREATE TABLE students (id bigserial primary key, name varchar(255));
INSERT INTO students VALUES ('Bob'), ('Bill'), ('John');

CREATE TABLE authors (id bigserial primary key, name varchar(255), surname varchar(255));
INSERT INTO authors VALUES ('Chuck', 'Palahniuk');
INSERT INTO authors VALUES ('Stephen', 'King');
INSERT INTO authors VALUES ('Erich-Maria', 'Remark');
INSERT INTO authors VALUES ('Bernard', 'Werber');
INSERT INTO authors VALUES ('Alexandre', 'Dumas');

CREATE TABLE novels (id bigserial primary key, title varchar(255), author_id integer references authors(id));
INSERT INTO novels VALUES ('Invisible Monsters', 1);
INSERT INTO novels VALUES ('Under the Dome', 2);
INSERT INTO novels VALUES ('Three comrades', 3);
INSERT INTO novels VALUES ('The Thanatonauts', 4);
INSERT INTO novels VALUES ('The Three Musketeerss', 4);

CREATE TABLE novel_readers (novel_id bigint REFERENCES novels(id),
                            student_id bigint REFERENCES students(id));
INSERT INTO novel_readers VALUES (1, 1);
INSERT INTO novel_readers VALUES (1, 2);
INSERT INTO novel_readers VALUES (2, 3);
INSERT INTO novel_readers VALUES (3, 4);
INSERT INTO novel_readers VALUES (4, 2);