DROP TABLE IF EXISTS inventory_transactions;
DROP TABLE IF EXISTS items;

CREATE TABLE items
(
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);
