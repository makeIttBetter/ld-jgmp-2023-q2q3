-- Inserting test data for User
INSERT INTO "user" (id, name, email)
VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'John Doe', 'john.doe@example.com');
INSERT INTO "user" (id, name, email)
VALUES ('e47ac10b-58cc-4372-a567-0e02b2c3d480', 'Jane Smith', 'jane.smith@example.com');
INSERT INTO "user" (id, name, email)
VALUES ('d47ac10b-58cc-4372-a567-0e02b2c3d481', 'Bob Alice', 'bob.alice@example.com');
INSERT INTO "user" (id, name, email)
VALUES ('c47ac10b-58cc-4372-a567-0e02b2c3d482', 'Eve Charlie', 'eve.charlie@example.com');

-- Inserting test data for Event
INSERT INTO event (id, title, date)
VALUES ('b47ac10b-58cc-4372-a567-0e02b2c3d483', 'Concert A', '2023-01-01T20:00:00');
INSERT INTO event (id, title, date)
VALUES ('a47ac10b-58cc-4372-a567-0e02b2c3d484', 'Concert B', '2023-01-05T21:00:00');
INSERT INTO event (id, title, date)
VALUES ('947ac10b-58cc-4372-a567-0e02b2c3d485', 'Theatre Play', '2023-02-10T19:00:00');
INSERT INTO event (id, title, date)
VALUES ('847ac10b-58cc-4372-a567-0e02b2c3d486', 'Football Match', '2023-03-15T15:00:00');

-- Inserting test data for Ticket
INSERT INTO ticket (id, user_id, event_id, place)
VALUES ('747ac10b-58cc-4372-a567-0e02b2c3d487', 'f47ac10b-58cc-4372-a567-0e02b2c3d479',
        'b47ac10b-58cc-4372-a567-0e02b2c3d483', 1);
INSERT INTO ticket (id, user_id, event_id, place)
VALUES ('647ac10b-58cc-4372-a567-0e02b2c3d488', 'e47ac10b-58cc-4372-a567-0e02b2c3d480',
        'b47ac10b-58cc-4372-a567-0e02b2c3d483', 2);
INSERT INTO ticket (id, user_id, event_id, place)
VALUES ('547ac10b-58cc-4372-a567-0e02b2c3d489', 'd47ac10b-58cc-4372-a567-0e02b2c3d481',
        'a47ac10b-58cc-4372-a567-0e02b2c3d484', 1);
INSERT INTO ticket (id, user_id, event_id, place)
VALUES ('447ac10b-58cc-4372-a567-0e02b2c3d490', 'c47ac10b-58cc-4372-a567-0e02b2c3d482',
        '947ac10b-58cc-4372-a567-0e02b2c3d485', 1);
