-- Init data for guest
INSERT INTO guest (card_id, name, is_checked_in, checked_in_date_time, is_checked_out, checked_out_date_time)
VALUES
('C2001', 'Guest Name 1', TRUE, TIMESTAMP '2024-05-20 10:01:00', FALSE, NULL),
('C2002', 'Guest Name 2', TRUE, TIMESTAMP '2024-05-20 10:02:00', FALSE, NULL),
('C2003', 'Guest Name 3', TRUE, TIMESTAMP '2024-05-20 10:03:00', TRUE, TIMESTAMP '2024-05-21 09:03:00'),
('C2004', 'Guest Name 4', FALSE, NULL, FALSE, NULL),
('C2005', 'Guest Name 5', TRUE, TIMESTAMP '2024-05-20 10:05:00', FALSE, NULL),
('C2006', 'Guest Name 6', TRUE, TIMESTAMP '2024-05-20 10:06:00', FALSE, NULL),
('C2007', 'Guest Name 7', TRUE, TIMESTAMP '2024-05-20 10:07:00', FALSE, NULL),
('C2008', 'Guest Name 8', TRUE, TIMESTAMP '2024-05-20 10:08:00', TRUE, TIMESTAMP '2024-05-21 09:08:00'),
('C2009', 'Guest Name 9', TRUE, TIMESTAMP '2024-05-20 10:09:00', FALSE, NULL),
('C2010', 'Guest Name 10', TRUE, TIMESTAMP '2024-05-20 10:10:00', FALSE, NULL),
('C2011', 'Guest Name 11', TRUE, TIMESTAMP '2024-05-20 10:11:00', FALSE, NULL),
('C2012', 'Guest Name 12', TRUE, TIMESTAMP '2024-05-20 10:12:00', FALSE, NULL),
('C2013', 'Guest Name 13', TRUE, TIMESTAMP '2024-05-20 10:13:00', TRUE, TIMESTAMP '2024-05-21 09:13:00'),
('C2014', 'Guest Name 14', FALSE, NULL, FALSE, NULL),
('C2015', 'Guest Name 15', TRUE, TIMESTAMP '2024-05-20 11:34:00', FALSE, NULL),
('C2016', 'Guest Name 16', TRUE, TIMESTAMP '2024-05-20 11:35:00', FALSE, NULL),
('C2017', 'Guest Name 17', TRUE, TIMESTAMP '2024-05-20 11:36:00', FALSE, NULL),
('C2018', 'Guest Name 18', TRUE, TIMESTAMP '2024-05-20 11:37:00', FALSE, NULL),
('C2019', 'Guest Name 19', TRUE, TIMESTAMP '2024-05-20 11:38:00', TRUE, TIMESTAMP '2024-05-21 10:38:00'),
('C2020', 'Guest Name 20', TRUE, TIMESTAMP '2024-05-20 11:39:00', FALSE, NULL);

-- Init data for parcel
INSERT INTO parcel (guest_id, description, is_picked_up) VALUES
(1,  'Parcel for Guest 1 - Delivered at 2024-05-21 08:01:00', FALSE),
(2,  'Parcel for Guest 2 - Delivered at 2024-05-21 08:02:00', FALSE),
(3,  'Parcel for Guest 3 - Delivered at 2024-05-21 08:03:00', FALSE),
(4,  'Parcel for Guest 4 - Delivered at 2024-05-21 08:04:00', FALSE),
(5,  'Parcel for Guest 5 - Delivered at 2024-05-21 08:05:00', TRUE),
(6,  'Parcel for Guest 6 - Delivered at 2024-05-21 08:06:00', TRUE),
(7,  'Parcel for Guest 7 - Delivered at 2024-05-21 08:07:00', TRUE),
(8,  'Parcel for Guest 8 - Delivered at 2024-05-21 08:08:00', FALSE),
(9,  'Parcel for Guest 9 - Delivered at 2024-05-21 08:09:00', FALSE),
(10, 'Parcel for Guest 10 - Delivered at 2024-05-21 08:10:00', FALSE);