DROP TABLE IF EXISTS Category;

CREATE TABLE Category
(
    id   int PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(255)
);

DROP TABLE IF EXISTS Hardware;

CREATE TABLE Hardware
(
    id         int PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    code       VARCHAR(100),
    price      DECIMAL(10, 2),
    quantity   INT,
    categoryId INT,
    FOREIGN KEY (categoryId) REFERENCES Category (id)
);

INSERT INTO Category (ID, NAME, CODE)
VALUES (1, 'CPU', 'New');
INSERT INTO Category (ID, NAME, CODE)
VALUES (2, 'GPU', 'New and used');
INSERT INTO Category (ID, NAME, CODE)
VALUES (3, 'MBO', 'New and used');
INSERT INTO Category (ID, NAME, CODE)
VALUES (4, 'RAM', 'New and used');
INSERT INTO Category (ID, NAME, CODE)
VALUES (5, 'Storage', 'New and used');
INSERT INTO Category (ID, NAME, CODE)
VALUES (6, 'Other', 'New and used');

INSERT INTO HARDWARE(id, name, code, price, quantity, categoryId)
VALUES (1, 'Tesla Model Y', 'Electric car', 50000, 2, 1);

INSERT INTO HARDWARE(id, name, code, price, quantity, categoryId)
VALUES (2, 'Other Hardware', 'Motherboard', 20000, 7, 6);

INSERT INTO HARDWARE(id, name, code, price, quantity, categoryId)
VALUES (3, 'Storage', 'Diskovi', 10000, 77, 5);

INSERT INTO HARDWARE(id, name, code, price, quantity, categoryId)
VALUES (4, 'CPU2', 'Intel77', 60000, 299, 1);

SELECT *
FROM Hardware;