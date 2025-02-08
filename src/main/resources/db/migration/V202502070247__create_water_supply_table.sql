CREATE TABLE IF NOT EXISTS water_supply
(
    id SERIAL PRIMARY KEY,
    city_name VARCHAR(255) NOT NULL,
    supply_date DATE NOT NULL,
    water_volume DOUBLE PRECISION NOT NULL
);