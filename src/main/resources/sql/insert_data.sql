INSERT INTO usr (username, password,  email, is_active)
VALUES('admin', 'admin', 'admin@gmail.com', true),
('manager', 'manager', 'manager@gmail.com', true);

INSERT INTO user_role(username, role)
VALUES ('admin', 'ADMIN'),
('manager', 'MANAGER');

INSERT INTO tour(tour_name, price, count_of_people, start_date, end_date, departing_from,
 country, locality, hotel_name)
 VALUES ('best_tour', 199.99, 4, '2021-07-22', '2021-08-04', 'Kiev National Airport',
 'Turkey', 'Marmaris', 'Best Hotel'),
 ('good_tour', 99.99, 2, '2021-09-02', '2021-09-06', 'Kiev National Airport', 'Egypt', 'Kair', 'good-hotel'),
 ('bad_tour', 50.00, 1, '2021-10-11', '2021-10-13', 'Kiev National Airport', 'Poland', 'Gdansk', 'bad_hotel');

 INSERT INTO tour_status(tour_name, tour_status)
 VALUES('best_tour', 'REGISTERED'),
 ('good_tour', 'REGISTERED'),
 ('bad_tour', 'REGISTERED');

 INSERT INTO tour_type(tour_name, tour_type)
 VALUES('best_tour', 'REST'),
 ('good_tour', 'SHOPPING'),
 ('bad_tour', 'EXCURSION');

 INSERT INTO room_type(tour_name, room_type)
 VALUES ('best_tour', 'PRESIDENT'),
 ('good_tour', 'LUXE'),
 ('bad_tour', 'STANDARD');

 INSERT INTO hotel_stars(tour_name, hotel_stars)
 VALUES('best_tour', 'FIVE'),
 ('good_tour', 'THREE'),
 ('bad_tour', 'ONE');