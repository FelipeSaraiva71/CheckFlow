CREATE TABLE tenants
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome     VARCHAR(200) UNIQUE NOT NULL,

    email    VARCHAR(150),

    telefone VARCHAR(11),

    endereco VARCHAR(300),

    status   VARCHAR(10)  NOT NULL

)


