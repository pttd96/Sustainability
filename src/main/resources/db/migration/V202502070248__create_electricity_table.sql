CREATE TABLE IF NOT EXISTS electricity
(
    id VARCHAR(255) PRIMARY KEY,
    city_name VARCHAR(255) NOT NULL,
    usage_date DATE NOT NULL,
    consumption_kwh DOUBLE PRECISION NOT NULL
);