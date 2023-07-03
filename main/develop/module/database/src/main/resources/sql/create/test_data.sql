BEGIN;

INSERT INTO students (name, surname, date_of_birth, phone_number, primary_skill)
SELECT
        'Name ' || i,
        'Surname ' || i,
        current_date - ((random() * 30)::int + 18) * INTERVAL '1 year',
        '555-' || lpad(i::text, 4, '0'),
        'Skill ' || ((i - 1) % 10 + 1)
FROM generate_series(1, 100000) AS i;

INSERT INTO subjects (subject_name, tutor)
SELECT
        'Subject ' || i,
        'Tutor ' || ((i - 1) % 100 + 1)
FROM generate_series(1, 1000) AS i;

INSERT INTO exam_results (student_id, subject_id, mark)
SELECT
        (i % 100000) + 1,
        (i % 1000) + 1,
        (random() * 4)::int + 1
FROM generate_series(1, 1000000) AS i;

COMMIT;


INSERT INTO student_address (student_id, address)
SELECT
    i,
    'Street ' || i || ', City ' || (i % 100 + 1)
FROM generate_series(1, 100000) AS i;