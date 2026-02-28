CREATE TABLE Category
(
    id IDENTITY PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    code VARCHAR(100)
);

CREATE TABLE Hardware (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(100),
    price DECIMAL(10,2),
    quantity INT,
    categoryId  INT,
    FOREIGN KEY (categoryId) REFERENCES Category(id)
);
