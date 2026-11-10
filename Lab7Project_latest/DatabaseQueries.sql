-- Run this in MySQL Workbench (Docker MySQL connection)
CREATE DATABASE IF NOT EXISTS lab7db;
USE lab7db;

-- Removes the old simple table and creates the new one
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(50)  NOT NULL,
    last_name   VARCHAR(50)  NOT NULL,
    gender      VARCHAR(10)  NOT NULL,
    dob         DATE         NOT NULL,
    age         INT          NOT NULL,
    phone       VARCHAR(20)  NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    continent   VARCHAR(50)  NOT NULL,
    experience  TEXT,
    hobbies     TEXT,
    photo_path  VARCHAR(500)
);

INSERT INTO users (first_name, last_name, gender, dob, age, phone, email, continent, experience, hobbies, photo_path) VALUES
('John', 'Doe', 'Male', '1994-03-12', 32, '617-555-0101', 'john.doe@gmail.com', 'North America', '5 years in software testing', 'Reading, Chess', ''),
('Jane', 'Doe', 'Female', '1998-07-25', 28, '617-555-0102', 'jane.doe@gmail.com', 'North America', 'Frontend developer intern', 'Painting', ''),
('Priya', 'Sharma', 'Female', '2000-01-08', 26, '857-555-0103', 'priya.sharma@gmail.com', 'Asia', 'Data analyst for 2 years', 'Dancing, Music', '');

SELECT * FROM users;
