-- Insert data into Floors
INSERT INTO BuildingComponent (type) VALUES
( 'Lobby'),
( '1st Floor'),
( '2nd Floor'),
( '3rd Floor'),
( '4th Floor'),
( '5th Floor'),
('Roof'),
('Basement'),
('Parking'),
('Garden');

INSERT INTO Apartments (floor_id, number) VALUES
(101, 1),
(102, 1),
(201, 2),
(202, 2),
(301, 3),
(302, 3),
(401, 4),
(402, 4),
(501, 5),
(502, 5);

-- Insert data into ControlGroups
INSERT INTO ControlGroups (name) VALUES
('Residents'),
('Maintenance Staff'),
('Security'),
('Management'),
('Visitors'),
('Contractors'),
('Cleaning Staff'),
('Delivery Personnel'),
('Emergency Services'),
('IT Support');

-- Insert data into Persons
INSERT INTO Persons (full_name, control_group_id) VALUES
('Alice Johnson', 1),
('Bob Smith', 1),
('Charlie Brown', 2),
('Diana Prince', 2),
('Evan Turner', 3),
('Fiona Green', 3),
('George Martin', 4),
('Hannah White', 4),
('Ian Wright', 5),
('Jessica Black', 5);

-- Insert data into Allocations
INSERT INTO Allocations (apartment_id) VALUES
(1),  -- Allocating persons to apartment 101
(1),  -- Allocating another person to apartment 101
(2),  -- Allocating persons to apartment 102
(2),  -- Allocating another person to apartment 102
(3),  -- Allocating persons to apartment 201
(3),  -- Allocating another person to apartment 201
(4),  -- Allocating persons to apartment 202
(4),  -- Allocating another person to apartment 202
(5),  -- Allocating persons to apartment 301
(5);  -- Allocating another person to apartment 301

-- Insert data into AccessControls
INSERT INTO AccessControls (card_scanner_id, floor_access) VALUES
(1, '{"1": true, "2": true, "3": false}'),
(2, '{"1": true, "2": false, "3": true}'),
(3, '{"1": false, "2": true, "3": true}'),
(4, '{"1": true, "2": true, "3": true}'),
(5, '{"1": false, "2": false, "3": true}'),
(6, '{"1": true, "2": true, "3": false}'),
(7, '{"1": true, "2": false, "3": true}'),
(8, '{"1": false, "2": true, "3": true}'),
(9, '{"1": true, "2": true, "3": true}'),
(10, '{"1": false, "2": false, "3": true}');

-- Insert data into MaintenanceRequests
INSERT INTO MaintenanceRequests (status, issue_description, apartment_id, request_date, resolved_date) VALUES
(true, 'Leaky faucet', 1, NOW(), NULL),
(false, 'Broken window', 2, NOW(), NULL),
(true, 'Heating issue', 3, NOW(), NULL),
(false, 'Electrical fault', 4, NOW(), NULL),
(true, 'Clogged drain', 5, NOW(), NULL),
(false, 'Paint peeling', 1, NOW(), NULL),
(true, 'AC not cooling', 2, NOW(), NULL),
(false, 'Door jammed', 3, NOW(), NULL),
(true, 'Garage light out', 4, NOW(), NULL),
(false, 'Stove malfunction', 5, NOW(), NULL);

-- Insert data into CardScanners
INSERT INTO CardScanners (floor_id) VALUES
(1),
(2),
(3),
(4),
(5),
(-1),
(-2),
(6),
(-3),
(-1);
