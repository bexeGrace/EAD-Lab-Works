CREATE DATABASE BookstoreDB;

USE BookstoreDB;

-- Create the Tasks table
CREATE TABLE Books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(50) NOT NULL,
    price DOUBLE
);