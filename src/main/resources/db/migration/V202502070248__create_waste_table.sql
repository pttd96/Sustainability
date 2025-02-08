CREATE TABLE IF NOT EXISTS waste
(
    id VARCHAR(255) PRIMARY KEY,
    city_name VARCHAR(255) NOT NULL,
    collection_date DATE NOT NULL,
    waste_volume DOUBLE PRECISION NOT NULL,
    waste_type VARCHAR(255) NOT NULL
);