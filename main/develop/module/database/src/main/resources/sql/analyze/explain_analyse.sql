
CREATE EXTENSION IF NOT EXISTS pg_trgm;


-- Find user by name (exact match)
DROP INDEX IF EXISTS idx_students_name;
CREATE INDEX idx_students_name ON students USING hash (name);
CREATE INDEX idx_students_name ON students USING btree (name);
CREATE INDEX idx_students_name ON students USING gist (name gist_trgm_ops);
CREATE INDEX idx_students_name ON students USING gin (name gin_trgm_ops);

EXPLAIN ANALYZE SELECT * FROM students WHERE name = 'Name 50000';



DROP INDEX IF EXISTS idx_students_surname;
CREATE INDEX idx_students_surname ON students USING btree (surname);
CREATE INDEX idx_students_surname ON students USING hash (surname);
CREATE INDEX idx_students_surname ON students USING gist (surname gist_trgm_ops);
CREATE INDEX idx_students_surname ON students USING gin (surname gin_trgm_ops);

-- Find user by surname (partial match)
EXPLAIN ANALYZE SELECT * FROM students WHERE surname LIKE 'Surname 5%';

-- Find user with marks by user surname (partial match)
EXPLAIN ANALYZE
SELECT s.name, s.surname, e.mark
FROM students s
         JOIN exam_results e ON s.id = e.student_id
WHERE s.surname LIKE '%Surname 5%';



-- Find user by phone number (partial match)
DROP INDEX IF EXISTS idx_students_phone_number;
CREATE INDEX idx_students_phone_number ON students USING btree (phone_number);
CREATE INDEX idx_students_phone_number ON students USING hash (phone_number);
CREATE INDEX idx_students_phone_number ON students USING gist (phone_number gist_trgm_ops);
CREATE INDEX idx_students_phone_number ON students USING gin (phone_number gin_trgm_ops);

EXPLAIN ANALYZE SELECT * FROM students WHERE phone_number LIKE '555-05%';



