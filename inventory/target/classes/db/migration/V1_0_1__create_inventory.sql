CREATE TABLE inventory
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    sku_code VARCHAR(255)          NULL,
    stock    INT                   NULL,
    CONSTRAINT pk_inventory PRIMARY KEY (id)
);