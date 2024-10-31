-- Insert into Building
INSERT INTO building (name, address) VALUES
('Building A', '123 Main St'),
('Building B', '456 Oak Ave'),
('Building C', '789 Maple Rd');

-- Insert into Floor, referencing the correct building_id values from the building table
INSERT INTO floor (number, description, building_id) VALUES
('1', 'First Floor', 1),
('1A', 'First Floor', 1),
('2', 'Second Floor', 1),
('3', 'Third Floor', 1),
('4', 'Fourth Floor', 1),
('R', 'Roof', 1),
('1', 'First Floor', 2),
('1', 'First Floor ', 2),
('2', 'Second Floor', 2),
('R', 'Roof', 2),
('1', 'First Floor', 3),
('1', 'First Floor', 3),
('2', 'Second Floor', 3),
('3', 'Third Floor', 3),
('4', 'Fourth Floor', 3),
('5', 'Fifth Floor', 3),
('6', 'Sixth Floor', 3),
('R', 'Roof', 3);

-- Insert into Room
INSERT INTO room (number, description, floor_id) VALUES
-- Building A Rooms
('101', 'Room 101', 1),
('102', 'Room 102', 1),
('103', 'Lobby', 1),
('201', 'Room 201', 2),
('202', 'Room 202', 2),
('301', 'Room 301', 3),
('302', 'Gym', 3),
('R1', 'Roof Access', 6),

-- Building B Rooms
('401', 'Room 401', 4),
('402', 'Room 402', 4),
('403', 'Roof Access', 4),
('501', 'Room 501', 5),
('502', 'Room 502', 5),
('601', 'Room 601', 6),
('602', 'Gym', 6),
('R2', 'Roof Access', 2),

-- Building C Rooms
('701', 'Room 701', 7),
('702', 'Room 702', 7),
('703', 'Lobby', 7),
('801', 'Room 801', 8),
('802', 'Room 802', 8),
('901', 'Room 901', 9),
('902', 'Room 902', 9),
('R3', 'Roof Access', 3);

-- Insert into Person
INSERT INTO person (email, first_name, last_name, phone_number) VALUES
('tony.stark@marvel.com', 'Tony', 'Stark', '9876543210'),
('steve.rogers@marvel.com', 'Steve', 'Rogers', '9876543211'),
('natasha.romanoff@marvel.com', 'Natasha', 'Romanoff', '9876543212'),
('bruce.banner@marvel.com', 'Bruce', 'Banner', '9876543213'),
('thor.odinson@marvel.com', 'Thor', 'Odinson', '9876543214'),
('peter.parker@marvel.com', 'Peter', 'Parker', '9876543215'),
('wanda.maximoff@marvel.com', 'Wanda', 'Maximoff', '9876543216'),
('clint.barton@marvel.com', 'Clint', 'Barton', '9876543217'),
('stephen.strange@marvel.com', 'Stephen', 'Strange', '9876543218'),
('carol.danvers@marvel.com', 'Carol', 'Danvers', '9876543219');

-- Insert into CardScanner
INSERT INTO card_scanner (serial_no, make, model, room_id) VALUES
('S101', 'MakeA', 'ModelX', 1),
('S102', 'MakeB', 'ModelY', 2),
('S103', 'MakeC', 'ModelZ', 3),
('S104', 'MakeD', 'ModelW', 4),
('S105', 'MakeE', 'ModelV', 5),
('S106', 'MakeF', 'ModelU', 6),
('S107', 'MakeG', 'ModelT', 7),
('S108', 'MakeH', 'ModelS', 8),
('S109', 'MakeI', 'ModelR', 9),
('S110', 'MakeJ', 'ModelQ', 10);

-- Insert into ControlGroup
INSERT INTO control_group (name, description) VALUES
('Control Group A', 'Access to Second Floor'),
('Control Group B', 'Access to Second Floor, Roof, Access to Room 201'),
('Control Group C', 'Access to Second Floor, Third Floor, Gym'),
('Control Group D', 'Access to Third Floor, Roof, Fourth Floor'),
('Control Group E', 'Access to Fifth Floor, Lobby'),
('Control Group F', 'Access to Sixth Floor, Gym, Roof'),
('Control Group G', 'Access to Seventh Floor, Roof'),
('Control Group H', 'Access to Eighth Floor, Lobby'),
('Control Group I', 'Access to Ninth Floor, Gym'),
('Control Group J', 'Access to all floors in Building C');

-- Insert into AccessControl
INSERT INTO access_control (description, scanner_id, room_id) VALUES
('Main Entrance Control', 1, 1),
('Lobby Control', 2, 3),
('Conference Room Control', 3, 2),
('Roof Access Control', 4, 3),
('Gym Access Control', 5, 6),
('Roof Access Control 2', 6, 4),
('Gym Access Control 2', 7, 8),
('Lobby Access Control 2', 8, 7);

-- Insert into ControlGroupAccessControl
INSERT INTO control_group_access_control (control_group_id, access_control_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(3, 6),
(3, 7),
(3, 8),
(4, 1),
(4, 2),
(4, 3),
(5, 4),
(5, 5),
(6, 6),
(6, 7),
(7, 8);

-- Insert into ControlGroupPerson
INSERT INTO control_group_person (control_group_id, person_id, start_date, expiration_date) VALUES
(1, 1, '2024-01-01', '2025-01-01'),
(2, 2, '2024-02-01', '2025-02-01'),
(3, 3, '2024-03-01', '2025-03-01'),
(2, 4, '2024-04-01', '2025-04-01'),
(2, 5, '2024-05-01', '2025-05-01'),
(2, 6, '2024-06-01', '2025-06-01'),
(3, 7, '2024-07-01', '2025-07-01'),
(3, 8, '2024-08-01', '2025-08-01'),
(3, 9, '2024-09-01', '2025-09-01'),
(4, 10, '2024-10-01', '2025-10-01');

-- Insert into AccessLog
INSERT INTO access_log (scanner_id, person_id, access_time) VALUES
(1, 1, '2024-01-01T10:00:00'),
(2, 2, '2024-01-02T11:00:00'),
(3, 3, '2024-01-03T12:00:00'),
(4, 4, '2024-01-04T13:00:00'),
(5, 5, '2024-01-05T14:00:00'),
(6, 6, '2024-01-06T15:00:00'),
(7, 7, '2024-01-07T16:00:00'),
(8, 8, '2024-01-08T17:00:00'),
(9, 9, '2024-01-09T18:00:00'),
(10, 10, '2024-01-10T19:00:00');

-- Insert into AccessRequest
INSERT INTO access_request (access_control_id, person_id, request_time, approved, scanner_id) VALUES
(1, 1, '2024-01-01T09:00:00', TRUE, 1),
(2, 2, '2024-01-02T10:00:00', TRUE, 2),
(3, 3, '2024-01-03T11:00:00', FALSE, 3),
(1, 4, '2024-01-04T12:00:00', TRUE, 4),
(1, 5, '2024-01-05T13:00:00', TRUE, 5),
(2, 6, '2024-01-06T14:00:00', FALSE, 6),
(2, 7, '2024-01-07T15:00:00', TRUE, 7),
(3, 8, '2024-01-08T16:00:00', FALSE, 8),
(1, 9, '2024-01-09T17:00:00', TRUE, 9),
(1, 10, '2024-01-10T18:00:00', FALSE, 10);


-- Insert into MaintenanceRequest
INSERT INTO maintenance_request (created_date, end_date, issue, status, person_id, room_id) VALUES
('2024-01-01T08:00:00', '2024-01-02T08:00:00', 'Leaking Pipe', 'PENDING', 1, 1),
('2024-01-03T09:00:00', '2024-01-04T09:00:00', 'Broken Window', 'DENIED', 2, 2),
('2024-01-05T10:00:00', '2024-01-07T10:00:00', 'Faulty AC', 'DONE', 3, 3),
('2024-01-07T11:00:00', '2024-01-08T11:00:00', 'Lighting Issue', 'DENIED', 4, 4),
('2024-01-09T12:00:00', '2024-01-10T12:00:00', 'Power Outage', 'PENDING', 5, 5),
('2024-01-11T13:00:00', '2024-01-15T13:00:00', 'Water Heater Issue', 'DONE', 6, 6),
('2024-01-13T14:00:00', '2024-01-14T14:00:00', 'Clogged Drain', 'DENIED', 7, 7),
('2024-01-15T15:00:00', '2024-01-16T15:00:00', 'Broken Door', 'PENDING', 8, 8),
('2024-01-17T16:00:00', '2024-01-22T16:00:00', 'Cracked Wall', 'DONE', 9, 9),
('2024-01-19T17:00:00', '2024-01-20T17:00:00', 'Noisy HVAC', 'PENDING', 10, 10);
