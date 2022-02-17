CREATE DATABASE authdb;
CREATE USER ecommerce WITH PASSWORD 'ecommerce';
GRANT ALL PRIVILEGES ON DATABASE "authdb" to ecommerce;