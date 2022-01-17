/* WARNING: put %% if you're using LIKE statement */

/* FILMS */
/**
  GET film/{storeId}
  FilmDao.getMovies
  Gets all movies from a store
 */
SELECT f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language, f.length,
       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost,
       count(*) as quantity
FROM film f
    LEFT JOIN language l ON ( f.language_id = l.language_id)
    LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    i.store_id = ?
GROUP BY f.film_id;

select * from film f, language l, inventory i
where f.language_id = l.language_id
  and i.film_id = f.film_id
  and i.store_id = 2;

/* Estrenos */
select f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language, f.length,
       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost,
       count(*) as quantity
from film f
    LEFT JOIN language l ON ( f.language_id = l.language_id)
    LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    i.store_id = ?
GROUP BY f.film_id, i.last_update
order by i.last_update desc limit 10;

/* mas rentadas de la semana */

SELECT *
FROM film f
         LEFT JOIN language l ON ( f.language_id = l.language_id)
         LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
         LEFT JOIN inventory i ON (f.film_id = i.film_id);

select f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language, f.length,
       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost,
       count(*) as quantity
from film f
    LEFT JOIN language l ON ( f.language_id = l.language_id)
    LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    i.store_id = ?
GROUP BY f.film_id, i.last_update
order by i.last_update desc;

SELECT f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language, f.length,
       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost,
       count(*) as quantity
FROM film f
    LEFT JOIN language l ON ( f.language_id = l.language_id)
    LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    i.store_id = ?
GROUP BY f.film_id
ORDER BY rand()
    LIMIT 1;



/**
  Random movies
 */
SELECT COUNT(r.inventory_id) as times_rented, i.inventory_id, i.film_id
FROM rental r
         LEFT JOIN inventory i on r.inventory_id = i.inventory_id
         LEFT JOIN staff s on s.staff_id = r.staff_id
WHERE s.staff_id = ?
  AND r.rental_date between ? and ?
GROUP BY i.film_id, r.inventory_id
ORDER BY times_rented desc limit 10;

SELECT COUNT(r.inventory_id) as times_rented, i.inventory_id, i.film_id
FROM rental r
         LEFT JOIN inventory i on r.inventory_id = i.inventory_id
         LEFT JOIN staff s on s.staff_id = r.staff_id
WHERE s.staff_id = ?
GROUP BY r.inventory_id
ORDER BY times_rented desc limit 10;

select * from staff, store;


SELECT f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language,
       f.length, f.rating, f.special_features,
       f.rental_duration, f.rental_rate, f.replacement_cost,
       count(*) as quantity
FROM film f
    LEFT JOIN language l ON ( f.language_id = l.language_id)
    LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    i.store_id = ?
  and f.film_id = ?
GROUP BY f.film_id;

/**
  GET film/{storeId}?title=someTitle
  FilmDao.findByTitle
  Search a movie by a title
 */
SELECT f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language, f.length,
       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost
FROM film f
    LEFT JOIN language l ON ( f.language_id = l.language_id)
    LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    UPPER(title) LIKE (?)
  AND i.store_id = ?;

select * from film f, language l, inventory i
where f.language_id = l.language_id
  and i.film_id = f.film_id
  and i.store_id = 2
  and f.title like ('%MAUDE%');

/**
  GET film/{storeId}?actor=someActor
  FilmDao.findByActor
  Search a movie by Actor
 */

SELECT f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language, f.length,
       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost
FROM film f
    LEFT JOIN language l ON (f.language_id = l.language_id)
    LEFT JOIN language ol ON (f.original_language_id = ol.language_id)
    LEFT JOIN film_actor fa on (f.film_id = fa.film_id)
    LEFT JOIN actor a on (fa.actor_id = a.actor_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    UPPER(CONCAT(a.first_name, ' ', a.last_name)) LIKE (?)
  AND i.store_id = ?;

select * from film f, language l, inventory i, actor a, film_actor fa
where f.language_id = l.language_id
  and i.film_id = f.film_id
  and i.store_id = 1
  and f.film_id = fa.film_id
  and fa.actor_id = a.actor_id
  and upper(concat(a.first_name, ' ', a.last_name)) like '%GINA%';

/**
  GET film/{storeId}?actor=someActor&title=someTitle
  FilmDao.findByActorAndTitle
  Search a movie by Actor and Title
 */

SELECT f.film_id, f.title, f.description, f.release_year,
       l.name as language , ol.name as original_language, f.length,
       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost
FROM film f
    LEFT JOIN language l ON (f.language_id = l.language_id)
    LEFT JOIN language ol ON (f.original_language_id = ol.language_id)
    LEFT JOIN film_actor fa on (f.film_id = fa.film_id)
    LEFT JOIN actor a on (fa.actor_id = a.actor_id)
    LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
    UPPER(CONCAT(a.first_name, ' ', a.last_name)) LIKE (?)
  AND UPPER(f.title) LIKE (?)
  AND i.store_id = ?;

select * from film f, language l, inventory i, actor a, film_actor fa
where f.language_id = l.language_id
  and i.film_id = f.film_id
  and i.store_id = 2
  and f.film_id = fa.film_id
  and fa.actor_id = a.actor_id
  and upper(concat(a.first_name, ' ', a.last_name)) like '%GINA%'
  and upper(f.title) like '%WINDOW%';

/* STORES */
/**
  GET store
  StoreDao.getStores
  Get all stores
 */
SELECT s.store_id, c2.country
FROM store s
         LEFT JOIN address a on s.address_id = a.address_id
         LEFT JOIN city c on a.city_id = c.city_id
         LEFT JOIN country c2 on c.country_id = c2.country_id;

/**
  GET store/{storeId}
  StoreDao.findStoreById
  Get a specific store
 */
SELECT s.store_id, c2.country
FROM store s
         LEFT JOIN address a on s.address_id = a.address_id
         LEFT JOIN city c on a.city_id = c.city_id
         LEFT JOIN country c2 on c.country_id = c2.country_id
WHERE
        s.store_id = ?;

/* ADDRESSES */
/**
  GET address/{id}
  AddressDao.findAddressById
  Get an address by ID
 */
SELECT a.address_id, a.address, a.address2, a.district,
       c.city, c2.country, a.postal_code, a.phone,
       ST_AsText(a.location) as location
FROM address a
         LEFT JOIN city c on a.city_id = c.city_id
         LEFT JOIN country c2 on c.country_id = c2.country_id
WHERE
        a.address_id = ?;

/**
  GET address/country
  AddressDao.getCountries
  Get all countries
 */
SELECT co.country_id, co.country
FROM country co;

/**
  POST /address
  AddressDao.createAddress
  Creates a new address
 */
INSERT INTO address (address, address2, district,
                     city_id, postal_code, phone, location)
VALUES ('Calle 1', '', 'Murillo', 52,
        '0000', '77777777', ST_GEOMFROMTEXT('POINT(1 1)'));

select * from address;

/**
  GET address/city
  AddressDao.findCityByCountry
  Get all cities in a country
 */
SELECT ci.city_id, ci.city, co.country_id
FROM city ci
         LEFT JOIN country co on co.country_id = ci.country_id
WHERE
        ci.country_id = ?;

select * from city where country_id = 14;

/* USERS */
/**
  GET user/{userId}
  UserDao.findUserById
  Gets user info by ID
 */
SELECT c.customer_id, c.store_id, c.first_name,
       c.last_name, c.email, c.address_id, c.active,
       c.create_date
FROM customer c
WHERE
        c.customer_id = ?;

/**
  POST /user
  UserDao.createUser
  Creates a new user
 */
INSERT INTO customer (store_id, first_name, last_name,
                      email, address_id, active, create_date,
                      last_update)
VALUES (1, 'ALEXANDER', 'SOSA', 'ALEXANDER.SOSA@noreply.com',
        5, 1, '2021-11-23 00:29:45', '2021-11-23 00:29:45');

select * from customer;
delete from customer where customer_id = 600;

/**
  PUT /user/{userId}
  UserDao.updateUser
  Updates info about an existent user
 */
UPDATE customer
SET store_id = 2, first_name = 'ALEX', last_name = 'SOSA',
    email = 'ALEX.SOSA@noreply.com', address_id = 10
WHERE customer_id = ?;

/* RENTS */
/**
  POST /rent
  RentDao.registerRent
  Makes a rent for a film
 */
INSERT INTO rental (rental_date, inventory_id,
                    customer_id, return_date, staff_id)
VALUES ('2005-05-25 11:09:48', 1, 601, '2005-05-25 11:09:48', 1);

select * from rental;
delete from rental where rental_id = ?;

/* RentDao.findRentById */
SELECT r.rental_id, r.rental_date, r.inventory_id,
       r.customer_id, r.return_date
FROM rental r
WHERE
        r.rental_id = ?;

/**
  POST /payment
  RentDao.registerPayment
  Makes a payment for a rent
 */
INSERT INTO payment (customer_id, staff_id, rental_id, amount, payment_date)
VALUES (602, 1, 50, 50.2, '2021-11-28 00:29:45');

/* RentDao.findPaymentById */
SELECT  p.payment_id, p.customer_id, p.rental_id,
        p.amount, p.payment_date
FROM payment p
WHERE p.payment_id = ?;

select * from payment;


/* general checks */
select * from address where address_id = 9;
select * from customer;
select * from store;

/*
  address 1: Canada (20)
  address 2: Australia (8)
  */
select * from address a, city c, country co
where a.address_id = 2
  and a.city_id = c.city_id
  and c.country_id = co.country_id;

/* Inventory movies */
SELECT i.inventory_id
FROM inventory i
         LEFT JOIN film f on f.film_id = i.film_id
         LEFT JOIN store s on i.store_id = s.store_id
WHERE
        f.film_id = ?
  AND s.store_id = ?
  AND i.inventory_id NOT IN (
    SELECT i2.inventory_id
    FROM inventory i2
             LEFT JOIN rental r on i2.inventory_id = r.inventory_id
    WHERE
            i2.film_id = ?
      AND i2.store_id = ?
      AND return_date > ?
);


select * from inventory i, film f, store s
where i.film_id = f.film_id
  and f.film_id = ?
  and s.store_id = i.store_id
  and s.store_id = ?
  and i.inventory_id not in(
    select i2.inventory_id
    from inventory i2, rental r
    where i2.inventory_id = r.inventory_id
      and i2.film_id = ?
      and i2.store_id = ?
      and r.return_date > ?
);

SELECT count(*) as original_quantity, f.title
FROM film f
         LEFT JOIN language l ON ( f.language_id = l.language_id)
         LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)
         LEFT JOIN inventory i ON (f.film_id = i.film_id)
WHERE
        i.store_id = ?
  and f.film_id = ?
GROUP BY f.film_id;

SELECT *
FROM inventory i2
         LEFT JOIN rental r on i2.inventory_id = r.inventory_id
WHERE
        i2.film_id = ?
  AND i2.store_id = ?
  AND return_date > ?;

select * from rental;
delete from rental where rental_id = 16084;

/* Inventory count */
SELECT DISTINCT i.inventory_id
FROM inventory i
         LEFT JOIN rental r on i.inventory_id = r.inventory_id
WHERE i.film_id = ?
  AND i.store_id = ?
  AND r.return_date < ?
GROUP BY i.inventory_id;

select distinct i2.inventory_id
from inventory i2, rental r
where i2.inventory_id = r.inventory_id
  and i2.film_id = ?
  and i2.store_id = ?
  and r.return_date < ?;

insert into rental (rental_date, inventory_id, customer_id, return_date, staff_id)
values ('2021-12-02', 10, 602, '2021-12-12', 2);



