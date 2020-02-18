/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username)
values (1, now(), false, 'OFFLINE',
        'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username)
values (2, now(), false, 'OFFLINE',
        'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username)
values (3, now(), false, 'OFFLINE',
        'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username)
values (4, now(), false, 'ONLINE',
        'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username)
values (5, now(), false, 'ONLINE',
        'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username)
values (6, now(), false, 'ONLINE',
        'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values (7,
        'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(),
        now(), false, 'OFFLINE',
        'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values (8,
        'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(),
        now(), false, 'ONLINE',
        'driver08pw', 'driver08');


-- create manufacturer

insert into manufacturer(id, manufacturer_name, logo, deleted, date_created, updated_date)
values (1, 'Mercedes-Benz', 'https://logos-download.com/wp-content/uploads/2016/02/Mercedes_Benz_logo_gradient.svg', false, now(), now());

insert into manufacturer(id, manufacturer_name, logo, deleted, date_created, updated_date)
values (2, 'Audi', '', false, now(), now());

insert into manufacturer(id, manufacturer_name, logo, deleted, date_created, updated_date)
values (3, 'Nissan', '', false, now(), now());


-- create car
insert into car (id, license_plate, seat_count, convertible, rating, engine_type, model, manufacturer_id, deleted, date_created, car_updated_date)
values (1, 'DL-7SX-2805', 4, true, '4.5', 'ELECTRIC', 'Sedan', 1, false, now(), now());

insert into car (id, license_plate, seat_count, convertible, rating, engine_type, model, manufacturer_id, deleted, date_created, car_updated_date)
values (2, 'DL-7SX-2806', 6, true, '4.0', 'GAS', 'Sedan', 2, false, now(), now());

insert into car (id, license_plate, seat_count, convertible, rating, engine_type, model, manufacturer_id, deleted, date_created, car_updated_date)
values (3, 'DL-7SX-2807', 4, true, '3.9', 'PATROL', 'Sedan', 3, false, now(), now());
