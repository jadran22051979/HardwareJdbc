INSERT INTO CATEGORY (ID, NAME, CODE)
VALUES (1, 'CPU', 'New');
INSERT INTO CATEGORY (ID, NAME, CODE)
VALUES (2, 'GPU', 'New and used');
INSERT INTO CATEGORY (ID, NAME, CODE)
VALUES (3, 'MBO', 'New and used');
INSERT INTO CATEGORY (ID, NAME, CODE)
VALUES (4, 'RAM', 'New and used');
INSERT INTO CATEGORY (ID, NAME, CODE)
VALUES (5, 'Storage', 'New and used');
INSERT INTO CATEGORY (ID, NAME, CODE)
VALUES (6, 'Other', 'New and used');

INSERT INTO HARDWARE(name, code, price, quantity, categoryId)
VALUES ('CPU', 'Intel5', 50000, 6, 1);

INSERT INTO HARDWARE(name, code, price, quantity, categoryId)
VALUES ('Other Hardware', 'Motherboard', 80000, 7, 6);

INSERT INTO HARDWARE(name, code, price, quantity, categoryId)
VALUES ('Storage', 'Disks', 90000, 8, 5);

INSERT INTO HARDWARE(name, code, price, quantity, categoryId)
VALUES ('CPU2', 'Intel7', 30000, 9, 1);