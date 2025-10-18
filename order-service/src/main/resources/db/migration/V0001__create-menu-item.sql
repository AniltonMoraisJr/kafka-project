CREATE TABLE MenuItem (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    category ENUM('ENTRADAS', 'PRATOS_PRINCIPAIS', 'BEBIDAS', 'SOBREMESA') NOT NULL,
    price DECIMAL(9, 2) NOT NULL,
    promotionalPrice DECIMAL(9, 2)
);

CREATE TABLE MenuItem_SEQ (
    next_val BIGINT
);