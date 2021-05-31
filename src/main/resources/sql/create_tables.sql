DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS credit_card;
DROP TABLE IF EXISTS room_type;
DROP TABLE IF EXISTS tour_type;
DROP TABLE IF EXISTS hotel_stars;
DROP TABLE IF EXISTS tour_status;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS usr;

CREATE TABLE usr(
id SERIAL,
username VARCHAR(30) UNIQUE ,
password VARCHAR (45) NOT NULL,
email VARCHAR(50) NOT NULL,
is_active BOOLEAN DEFAULT TRUE ,
PRIMARY KEY(id)
);

CREATE TABLE user_role(
user_role_id SERIAL,
username VARCHAR (30) NOT NULL,
role VARCHAR(100),
PRIMARY KEY (user_role_id),
FOREIGN KEY(username) REFERENCES usr(username) ON DELETE RESTRICT
);

CREATE TABLE credit_card(
credit_card_id SERIAL,
username VARCHAR (30) NOT NULL,
card_number VARCHAR(19) UNIQUE,
card_password VARCHAR(60) NOT NULL,
balance DECIMAL,
PRIMARY KEY (credit_card_id),
FOREIGN KEY (username) REFERENCES usr(username) ON DELETE RESTRICT
);

CREATE TABLE tour(
tour_id SERIAL,
tour_name VARCHAR(30) UNIQUE ,
count_of_people INT NOT NULL,
price DECIMAL NOT NULL,
start_date TIMESTAMP NOT NULL,
end_date TIMESTAMP NOT NULL,
departing_from VARCHAR(100) NOT NULL,
country VARCHAR(100) NOT NULL,
locality VARCHAR(100) NOT NULL,
hotel_name VARCHAR(100) NOT NULL,
is_all_inclusive BOOLEAN DEFAULT FALSE,
is_hot BOOLEAN DEFAULT FALSE,
username VARCHAR (30),
PRIMARY KEY (tour_id),
FOREIGN KEY (username) REFERENCES usr(username) ON DELETE RESTRICT
);

CREATE TABLE room_type(
room_type_id SERIAL,
tour_name VARCHAR (30) NOT NULL,
room_type VARCHAR(100),
PRIMARY KEY (room_type_id),
FOREIGN KEY (tour_name) REFERENCES tour(tour_name) ON DELETE RESTRICT
);

CREATE TABLE tour_type(
tour_type_id SERIAL,
tour_name  VARCHAR (30)  NOT NULL,
tour_type VARCHAR(100),
PRIMARY KEY (tour_type_id),
FOREIGN KEY (tour_name) REFERENCES tour(tour_name) ON DELETE RESTRICT
);

CREATE TABLE hotel_stars(
hotel_stars_id SERIAL,
tour_name VARCHAR (30)  NOT NULL,
hotel_stars VARCHAR(100),
PRIMARY KEY (hotel_stars_id),
FOREIGN KEY (tour_name) REFERENCES tour(tour_name) ON DELETE RESTRICT
);

CREATE TABLE tour_status(
tour_status_id SERIAL,
tour_name VARCHAR(30) NOT NULL,
tour_status VARCHAR(100),
PRIMARY KEY (tour_status_id),
FOREIGN KEY (tour_name) REFERENCES tour(tour_name) ON DELETE RESTRICT
);
