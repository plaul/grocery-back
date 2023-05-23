CREATE TABLE ProductOrder
(
    id          INT AUTO_INCREMENT NOT NULL,
    product_id  INT NULL,
    delivery_id INT NULL,
    quantity    INT NOT NULL,
    CONSTRAINT pk_productorder PRIMARY KEY (id)
);

CREATE TABLE UserWithRoles
(
    username           VARCHAR(50) NOT NULL,
    DISCRIMINATOR_TYPE VARCHAR(31) NULL,
    email              VARCHAR(50) NOT NULL,
    password           VARCHAR(72) NOT NULL,
    enabled            BIT(1)      NOT NULL,
    created            datetime NULL,
    edited             datetime NULL,
    CONSTRAINT pk_userwithroles PRIMARY KEY (username)
);

ALTER TABLE security_role
    ADD UserWithRoles_username VARCHAR(50) NULL;

ALTER TABLE security_role
    MODIFY UserWithRoles_username VARCHAR (50) NOT NULL;

ALTER TABLE Delivery
    ADD deliveryDate date NULL;

ALTER TABLE Delivery
    ADD fromWareHouse VARCHAR(15) NULL;

ALTER TABLE security_role
    ADD CONSTRAINT uc_security_role_userwithroles_username UNIQUE (UserWithRoles_username);

ALTER TABLE UserWithRoles
    ADD CONSTRAINT uc_userwithroles_email UNIQUE (email);

ALTER TABLE UserWithRoles
    ADD CONSTRAINT uc_userwithroles_username UNIQUE (username);

ALTER TABLE ProductOrder
    ADD CONSTRAINT FK_PRODUCTORDER_ON_DELIVERY FOREIGN KEY (delivery_id) REFERENCES Delivery (id);

ALTER TABLE ProductOrder
    ADD CONSTRAINT FK_PRODUCTORDER_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES Product (id);

ALTER TABLE security_role
    ADD CONSTRAINT fk_security_role_on_user_with_roles FOREIGN KEY (UserWithRoles_username) REFERENCES UserWithRoles (username);

ALTER TABLE security_role
DROP
FOREIGN KEY FKcaetwsdb6qvbhum0rlf48px8o;

ALTER TABLE product_order
DROP
FOREIGN KEY FKgtwrcetl5qwlfcuwujcrlyfl0;

ALTER TABLE product_order
DROP
FOREIGN KEY FKh73acsd9s5wp6l0e55td6jr1m;

DROP TABLE customer;

DROP TABLE product_order;

DROP TABLE user_with_roles;

ALTER TABLE delivery
DROP
COLUMN delivery_date;

ALTER TABLE delivery
DROP
COLUMN from_ware_house;

ALTER TABLE security_role
DROP
COLUMN user_with_roles_username;

ALTER TABLE security_role
DROP
COLUMN roles;

ALTER TABLE security_role
    ADD roles ENUM ('USER', 'ADMIN') NULL;