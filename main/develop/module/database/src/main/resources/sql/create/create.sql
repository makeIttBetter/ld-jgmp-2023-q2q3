CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          name TEXT NOT NULL CHECK (name !~* '[#$@]'),
                          surname TEXT NOT NULL,
                          date_of_birth DATE NOT NULL,
                          phone_number TEXT,
                          primary_skill TEXT,
                          created_datetime TIMESTAMP NOT NULL DEFAULT current_timestamp,
                          updated_datetime TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE subjects (
                          id SERIAL PRIMARY KEY,
                          subject_name TEXT NOT NULL,
                          tutor TEXT NOT NULL
);

CREATE TABLE exam_results (
                              id SERIAL PRIMARY KEY,
                              student_id INTEGER REFERENCES students(id),
                              subject_id INTEGER REFERENCES subjects(id),
                              mark INTEGER CHECK (mark BETWEEN 1 AND 5),
                              exam_date TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE student_address
(
    id         SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES students (id),
    address    TEXT NOT NULL
);

