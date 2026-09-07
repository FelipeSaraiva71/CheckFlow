CREATE TABLE usuarios
(

    id        BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome      VARCHAR(100) NOT NULL,

    sobrenome VARCHAR(100) NOT NULL,

    telefone  VARCHAR(20),

    email     VARCHAR(150) NOT NULL UNIQUE,

    password  VARCHAR(255) NOT NULL,

    status VARCHAR (10) NOT NULL,

    tipo      VARCHAR(20)  NOT NULL,

    tenant_id   BIGINT,

    criado_Por BIGINT ,

    criado_Em TIMESTAMP NOT NULL,

    atualizado_Por BIGINT,

    atualizado_Em TIMESTAMP

);