CREATE TABLE usuarios
(

    id        BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome      VARCHAR(100) NOT NULL,

    sobrenome VARCHAR(100) NOT NULL,

    telefone  VARCHAR(20) NOT NULL,

    email     VARCHAR(150) NOT NULL UNIQUE,

    password  VARCHAR(255) NOT NULL,

    status VARCHAR (10) NOT NULL,

    tipo      VARCHAR(20)  NOT NULL,

    criado_por BIGINT NOT NULL,

    criado_em TIMESTAMP NOT NULL,

    atualizado_por BIGINT,

    atualizado_em TIMESTAMP,

    tenant_id   BIGINT

)