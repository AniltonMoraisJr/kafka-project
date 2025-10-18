-- tabela Order
CREATE TABLE CustomerOrder (
    id BIGINT PRIMARY KEY,
    orderDateTime DATETIME NOT NULL,
    status ENUM('REALIZADO', 'PAGO', 'CONFIRMADO', 'PRONTO', 'SAIU_PRA_ENTREGA', 'ENTREGUE') NOT NULL,
    customerName VARCHAR(100) NOT NULL,
    customerCpf VARCHAR(14) NOT NULL,
    customerPhone VARCHAR(16) NOT NULL,
    customerAddress VARCHAR(300) NOT NULL
);

CREATE TABLE CustomerOrder_SEQ (
    next_val BIGINT
);

-- tabela CustomerOrderItem
CREATE TABLE CustomerOrderItem (
    id BIGINT PRIMARY KEY,
    amount BIGINT NOT NULL,
    unitPrice DECIMAL(9, 2) NOT NULL,
    observation VARCHAR(300),
    customerOrder_id BIGINT NOT NULL,
    menuItem_id BIGINT NOT NULL,
    FOREIGN KEY (customerOrder_id) REFERENCES CustomerOrder(id),
    FOREIGN KEY (menuItem_id) REFERENCES MenuItem(id)
);

CREATE TABLE CustomerOrderItem_SEQ (
    next_val BIGINT
);