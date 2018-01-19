SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS happyhour (
    id int PRIMARY KEY auto_increment,
    description VARCHAR,
    price INTEGER,
    days VARCHAR,
    time VARCHAR,
    restaurantId VARCHAR
);

CREATE TABLE IF NOT EXISTS restaurant (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    type VARCHAR,
    averageRating FLOAT,
    ratings FLOAT,
    numberOfRatings INTEGER,
    review VARCHAR,
    street VARCHAR,
    city VARCHAR,
    state VARCHAR,
    zip VARCHAR
);