
-- Table for person
CREATE TABLE person (
    person_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15),
    email VARCHAR(100)
);

-- Drop the room table if it exists
DROP TABLE IF EXISTS public.room;
-- Create the room table
CREATE TABLE room (
    room_id SERIAL PRIMARY KEY,
    room_number VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);

-- Drop the building table if it exists
DROP TABLE IF EXISTS public.building;
-- Table for building
CREATE TABLE building (
    building_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    total_floors INT NOT NULL
);

-- Drop the floor table if it exists
DROP TABLE IF EXISTS public.floor;
-- Table for floor
CREATE TABLE floor (
    floor_id SERIAL PRIMARY KEY,
    building_id INT REFERENCES building(building_id) ON DELETE CASCADE,
    description VARCHAR(255) NOT NULL
);

-- Drop the apartment table if it exists
DROP TABLE IF EXISTS public.apartment;
-- Table for apartment
CREATE TABLE apartment (
    apartment_id SERIAL PRIMARY KEY,
    floor_id INT REFERENCES floor(floor_id) ON DELETE CASCADE,
);

-- Drop the access_control table if it exists
DROP TABLE IF EXISTS public.access_control;
-- Table for access control (manages who has access to which apartments)
CREATE TABLE access_control (
    access_control_id SERIAL PRIMARY KEY,
        person_id INT REFERENCES person(person_id) ON DELETE CASCADE,
        room_id INT REFERENCES room(room_id) ON DELETE CASCADE,
        control_group_id INT REFERENCES control_group(control_group_id) ON DELETE CASCADE,
        start_date TIMESTAMP NOT NULL,
        end_date TIMESTAMP
);

-- Drop the access_log table if it exists
DROP TABLE IF EXISTS public.access_log;
-- Table for access log (logs whenever a person uses a scanner)
CREATE TABLE access_log (
    access_log_id SERIAL PRIMARY KEY,
    person_id INT REFERENCES person(person_id) ON DELETE CASCADE,
    card_scanner_id INT REFERENCES card_scanner(card_scanner_id) ON DELETE CASCADE,
    access_time TIMESTAMP NOT NULL
);

-- Drop the card_scanner table if it exists
DROP TABLE IF EXISTS public.card_scanner;
-- Table for card scanners (located at various places, like entrances or elevators)
CREATE TABLE card_scanner (
    card_scanner_id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    floor_id INT REFERENCES floor(floor_id) ON DELETE CASCADE

-- Drop the maintenance_request table if it exists
DROP TABLE IF EXISTS public.maintenance_request;
CREATE TABLE maintenance_request (
    maintenance_request_id SERIAL PRIMARY KEY,
    request_date DATE NOT NULL,
    resolved_date DATE,
    issue_description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    apartment_id INT REFERENCES apartment(apartment_id) ON DELETE CASCADE,
    person_id INT REFERENCES person(person_id) ON DELETE CASCADE,


-- Drop the access_request table if it exists
DROP TABLE IF EXISTS public.access_request;
CREATE TABLE access_request (
     access_request_id SERIAL PRIMARY KEY,
       person_id INT REFERENCES person(person_id) ON DELETE CASCADE,
       control_group_id INT REFERENCES control_group(control_group_id) ON DELETE CASCADE,
       processed_by BIGINT REFERENCES person(person_id) ON DELETE SET NULL,
       ALTER TABLE access_request ALTER COLUMN processed_by TYPE BIGINT USING processed_by::BIGINT;
       requested_date TIMESTAMP NOT NULL,
       decision_date TIMESTAMP,
       reason VARCHAR(255) NOT NULL
);

