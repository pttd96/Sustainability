CREATE TABLE IF NOT EXISTS water_supply
(
    id VARCHAR(255) PRIMARY KEY,
    city_name VARCHAR(255) NOT NULL,
    supply_date DATE NOT NULL,
    water_volume DOUBLE PRECISION NOT NULL
);