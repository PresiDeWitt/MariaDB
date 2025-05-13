package foc.departamento.config;

import java.beans.Transient;

/**
 * Clase de configuración para almacenar las propiedades de la base de datos.
 * Centraliza los valores de configuración en una sola clase.
 */
public class DatabaseConfig {
    // Constantes de configuración de la base de datos
    public static final String DRIVER = "org.mariadb.jdbc.Driver";
    public static final String URL = "jdbc:mariadb://localhost:3306/departamentos";
    public static final String USUARIO = "root";
    public static final String PASSWORD = "password";

    // Constructor privado para evitar instancias
    private DatabaseConfig() {
        // Esta clase no debe ser instanciada
    }
}
/*
You must insert this code in MySQL before running the program and provide your username and password in the parameters.
__________________________________________________________________________________________________
-- Create the database with the ID name
CREATE DATABASE IF NOT EXISTS `departments`;

-- Use the database
USE `departments`;

-- Create the Departments table
CREATE TABLE Departments (
code INT PRIMARY KEY,
name VARCHAR(50),
location_id INT,
manager_id INT
);
___________________________________________________________________________________________________
 */
