CREATE DATABASE PokemonDB;
USE PokemonDB;

CREATE TABLE pokemon (
     id INT AUTO_INCREMENT PRIMARY KEY,
     pokedex_number INT NOT NULL,
     name VARCHAR(255) NOT NULL,
     type1 VARCHAR(255),
     type2 VARCHAR(255),
     gif_url VARCHAR(255)
);