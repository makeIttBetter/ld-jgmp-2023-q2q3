-- Dropping tables if they exist
DROP TABLE ticket IF EXISTS;
DROP TABLE event IF EXISTS;
DROP TABLE "user" IF EXISTS;

-- User table creation
CREATE TABLE "user"
(
    id    UUID PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
CREATE INDEX user_email ON "user" (email);

-- Event table creation
CREATE TABLE event
(
    id    UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    date  TIMESTAMP    NOT NULL,
    UNIQUE (title, date)

);
CREATE INDEX event_title ON event (title);

-- Ticket table creation
CREATE TABLE ticket
(
    id       UUID PRIMARY KEY,
    user_id  UUID NOT NULL,
    event_id UUID NOT NULL,
    place    INT  NOT NULL,
    UNIQUE (event_id, place)
);

ALTER TABLE ticket
    ADD CONSTRAINT fk_ticket_user FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE ticket
    ADD CONSTRAINT fk_ticket_event FOREIGN KEY (event_id) REFERENCES event (id);

CREATE INDEX ticket_user_id ON ticket (user_id);
CREATE INDEX ticket_event_id ON ticket (event_id);
