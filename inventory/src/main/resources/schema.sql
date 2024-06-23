DROP TABLE IF EXISTS inventory_transactions;
DROP TABLE IF EXISTS items;

CREATE TABLE items
(
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE inventory_transactions
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    type        VARCHAR(10) NOT NULL CHECK (type IN ('ADJUST', 'RECEIPT', 'ISSUE')),
    date_time   DATETIME NOT NULL,
    item_code   VARCHAR(10) NOT NULL,
    quantity    INTEGER NOT NULL,
    description VARCHAR(255),
    CONSTRAINT fk_item_code FOREIGN KEY (item_code) REFERENCES items(code)
);
