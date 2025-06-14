CREATE DATABASE IF NOT EXISTS lab8_clima;
USE lab8_clima;

CREATE TABLE monitoreo_climatico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ciudad VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    temp_promedio DECIMAL(5,2) NOT NULL,
    condicion_frecuente VARCHAR(100) NOT NULL,
    temp_max DECIMAL(5,2) NOT NULL,
    temp_min DECIMAL(5,2) NOT NULL
);