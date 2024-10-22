-- Drop tables if they already exist
DROP TABLE IF EXISTS control_group_access_control CASCADE;
DROP TABLE IF EXISTS control_group_person CASCADE;
DROP TABLE IF EXISTS access_control CASCADE;
DROP TABLE IF EXISTS maintenance_request CASCADE;
DROP TABLE IF EXISTS card_scanner CASCADE;
DROP TABLE IF EXISTS access_log CASCADE;
DROP TABLE IF EXISTS allocation CASCADE;
DROP TABLE IF EXISTS room CASCADE;
DROP TABLE IF EXISTS floor CASCADE;
DROP TABLE IF EXISTS building CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS control_group CASCADE;

-- Create tables
CREATE TABLE IF NOT EXISTS building (
  building_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS floor (
  floor_id BIGSERIAL PRIMARY KEY,
  number VARCHAR(10) NOT NULL,
  description VARCHAR(255),
  building_id BIGINT,
  FOREIGN KEY (building_id) REFERENCES building(building_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS room (
    room_id BIGSERIAL PRIMARY KEY,
    number VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    floor_id BIGINT,
    FOREIGN KEY (floor_id) REFERENCES floor(floor_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS person (
    person_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS card_scanner (
    scanner_id BIGSERIAL PRIMARY KEY,
    serial_no VARCHAR(100) NOT NULL,
    make VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    room_id BIGINT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room(room_id) ON DELETE CASCADE
);

-- Create the access_control table before referencing it elsewhere
CREATE TABLE IF NOT EXISTS access_control (
    access_control_id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),
    scanner_id BIGINT,
    room_id BIGINT NOT NULL,
    CONSTRAINT fk_card_scanner FOREIGN KEY (scanner_id) REFERENCES card_scanner(scanner_id) ON DELETE SET NULL,
    CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES room(room_id) ON DELETE CASCADE
);

-- Create the control_group table
CREATE TABLE IF NOT EXISTS control_group (
    control_group_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS control_group_person (
    control_group_id BIGINT,
    person_id BIGINT,
    start_date DATE NOT NULL,
    expiration_date DATE,
    PRIMARY KEY (control_group_id, person_id),
    FOREIGN KEY (control_group_id) REFERENCES control_group(control_group_id) ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS control_group_access_control (
    control_group_id BIGINT,
    access_control_id BIGINT,
    PRIMARY KEY (control_group_id, access_control_id),
    FOREIGN KEY (control_group_id) REFERENCES control_group(control_group_id) ON DELETE CASCADE,
    FOREIGN KEY (access_control_id) REFERENCES access_control(access_control_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS access_log (
    log_id BIGSERIAL PRIMARY KEY,
    person_id BIGINT NOT NULL,
    scanner_id BIGINT NOT NULL,
    access_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE,
    FOREIGN KEY (scanner_id) REFERENCES card_scanner(scanner_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS access_request (
    id BIGSERIAL PRIMARY KEY,
    access_control_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    request_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    approved BOOLEAN NOT NULL,
    scanner_id BIGINT NOT NULL,
    CONSTRAINT fk_access_control FOREIGN KEY (access_control_id) REFERENCES access_control(access_control_id) ON DELETE CASCADE,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE,
    CONSTRAINT fk_card_scanner FOREIGN KEY (scanner_id) REFERENCES card_scanner(card_scanner_id)
);

CREATE TABLE IF NOT EXISTS maintenance_request (
    maintenance_request_id BIGSERIAL PRIMARY KEY,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP,
    issue VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING', -- Assuming you want a default status
    person_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES room(room_id) ON DELETE CASCADE
);

