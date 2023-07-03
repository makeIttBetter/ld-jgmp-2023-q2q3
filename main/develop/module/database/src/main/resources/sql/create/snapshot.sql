CREATE MATERIALIZED VIEW students_snapshot AS
SELECT s.name, s.surname, sub.subject_name, e.mark
FROM students s
         JOIN exam_results e ON s.id = e.student_id
         JOIN subjects sub ON e.subject_id = sub.id;