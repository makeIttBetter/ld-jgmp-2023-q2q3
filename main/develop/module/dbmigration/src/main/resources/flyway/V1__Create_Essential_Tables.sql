-- 'A table to contain all the authors'
CREATE TABLE authors (

                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL
);

-- 'A table to contain all the books'
CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) UNIQUE NOT NULL,
                       author INT NOT NULL,
                       FOREIGN KEY (author) REFERENCES authors(id)
);
