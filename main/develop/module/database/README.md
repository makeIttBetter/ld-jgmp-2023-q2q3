# Database



## Task
Description
Please, complete the following task.

Design database for CDP program. Your DB should store information about students (name, surname, date of birth, phone numbers, primary skill, created_datetime, updated_datetime etc.), subjects (subject name, tutor, etc.) and exam results (student, subject, mark).
Please add appropriate constraints (primary keys, foreign keys, indexes, etc.).

Design such kind of database for PostrgeSQL. Show your design in some suitable way (PDF, PNG, etc). (1 point)

Try different kind of indexes (B-tree, Hash, GIN, GIST) for your fields. Analyze performance for each of the indexes (use ANALYZE and EXPLAIN). Check the size of the index. Try to set index before inserting test data and after. What was the time? Test data:

a. 100K of users

b. 1K of subjects

c. 1 million of marks

Test queries:

a. Find user by name (exact match)

b. Find user by surname (partial match)

c. Find user by phone number (partial match)

d. Find user with marks by user surname (partial match)

Add your investigations to separate document. (1 point)

5. Add trigger that will update column updated_datetime to current date in case of updating any of student. (0.3 point)

6. Add validation on DB level that will check username on special characters (reject student name with next characters '@', '#', '$'). (0.3 point)

7. Create snapshot that will contain next data: student name, student surname, subject name, mark (snapshot means that in case of changing some data in source table – your snapshot should not change). (0.3 point)

8. Create function that will return average mark for input user. (0.3 point)

9. Create function that will return avarage mark for input subject name. (0.3 point).

10. Create function that will return student at "red zone" (red zone means at least 2 marks <=3). (0.3 point)

11. Extra point (1 point). Show in tests (java application) transaction isolation phenomena. Describe what kind of phenomena is it and how did you achieve it.

12. Extra point 2 (1 point). Implement immutable data trigger. Create new table student_address. Add several rows with test data and do not give acces to update any information inside it. Hint: you can create trigger that will reject any update operation for target table, but save new row with updated (merged with original) data into separate table.

Other rules:

Please, add your changes to GIT task by task. It will be better to check your result based on such kind of history;
Database table/constraint creation – separate file;
Test data for indexes – separate file.
The result of your task is:

DB design in suitable format;
Index investigation document;
SQL file that will show to your mentor and tutor how you did your homework.


## Main Task requirements
src/main/resources folder contains completed main Task's requirements.

explain_analyse.sql - is like a base of sql scripts examples which allows you to manually recreate indexes and test select queries' performance.

create.sql - file to create tales.
functions.sql - file to create functions and trigger, according to the 5,8,9,10 requirements.
snapshot.sql - file to create a snapshot according to the requirement number 7.
test_data.sql - transactional sql script to generate test data for created tables.



## Extra Task requirements

Tasks for extra points will be made using Java code implementation

