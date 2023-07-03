-- Add trigger to update the updated_datetime column:
CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_datetime = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_students_timestamp
    BEFORE UPDATE
    ON students
    FOR EACH ROW
EXECUTE PROCEDURE update_timestamp();


-- Function to return average mark for a student:
CREATE OR REPLACE FUNCTION avg_mark_student(student_id INT)
    RETURNS FLOAT AS
$$
SELECT AVG(mark)
FROM exam_results
WHERE student_id = $1
$$ LANGUAGE sql;


-- Function to return average mark for a subject:
CREATE OR REPLACE FUNCTION avg_mark_subject(subject_id INT)
    RETURNS FLOAT AS
$$
SELECT AVG(mark)
FROM exam_results
WHERE subject_id = $1
$$ LANGUAGE sql;


-- Function to return students in the "red zone":
CREATE OR REPLACE FUNCTION students_red_zone()
    RETURNS TABLE
            (
                student_id INT,
                full_name  TEXT
            )
AS
$$
SELECT s.id, s.name || ' ' || s.surname
FROM students s
WHERE s.id IN (SELECT student_id
               FROM exam_results
               GROUP BY student_id
               HAVING COUNT(CASE WHEN mark <= 3 THEN 1 END) >= 2)
$$ LANGUAGE SQL;


CREATE OR REPLACE FUNCTION reject_updates_on_student_address() RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'UPDATE' THEN
        -- insert the new row into a separate table
        INSERT INTO updated_student_address SELECT NEW.*;
        -- raise an error to reject the update on the original table
        RAISE EXCEPTION 'Updates on student_address are not allowed';
    END IF;
    RETURN NULL; -- result is ignored since this is an AFTER trigger
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER student_address_update_trigger
    AFTER UPDATE ON student_address
    FOR EACH ROW EXECUTE PROCEDURE reject_updates_on_student_address();
