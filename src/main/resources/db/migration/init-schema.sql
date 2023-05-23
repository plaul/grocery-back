CREATE TABLE Delivery
(
    id            INT AUTO_INCREMENT NOT NULL,
    van_id        INT NULL,
    deliveryDate  date NULL,
    fromWareHouse VARCHAR(15) NULL,
    destination   VARCHAR(100) NULL,
    CONSTRAINT pk_delivery PRIMARY KEY (id)
);

CREATE TABLE Product
(
    id     INT AUTO_INCREMENT NOT NULL,
    name   VARCHAR(30) NULL,
    price  DOUBLE NOT NULL,
    weight INT    NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

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

CREATE TABLE Van
(
    id       INT AUTO_INCREMENT NOT NULL,
    brand    VARCHAR(50) NULL,
    model    VARCHAR(50) NULL,
    capacity INT NOT NULL,
    CONSTRAINT pk_van PRIMARY KEY (id)
);

CREATE TABLE security_role
(
    UserWithRoles_username VARCHAR(50) NOT NULL,
    roles                  ENUM ('USER', 'ADMIN') NULL
);

ALTER TABLE Product
    ADD CONSTRAINT uc_product_name UNIQUE (name);

ALTER TABLE security_role
    ADD CONSTRAINT uc_security_role_userwithroles_username UNIQUE (UserWithRoles_username);

ALTER TABLE UserWithRoles
    ADD CONSTRAINT uc_userwithroles_email UNIQUE (email);

ALTER TABLE UserWithRoles
    ADD CONSTRAINT uc_userwithroles_username UNIQUE (username);

ALTER TABLE Delivery
    ADD CONSTRAINT FK_DELIVERY_ON_VAN FOREIGN KEY (van_id) REFERENCES Van (id);

ALTER TABLE ProductOrder
    ADD CONSTRAINT FK_PRODUCTORDER_ON_DELIVERY FOREIGN KEY (delivery_id) REFERENCES Delivery (id);

ALTER TABLE ProductOrder
    ADD CONSTRAINT FK_PRODUCTORDER_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES Product (id);

ALTER TABLE security_role
    ADD CONSTRAINT fk_security_role_on_user_with_roles FOREIGN KEY (UserWithRoles_username) REFERENCES UserWithRoles (username);