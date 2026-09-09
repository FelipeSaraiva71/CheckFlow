CREATE TABLE tenant
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome VARCHAR(200) NOT NULL ,

    status VARCHAR (10) NOT NULL,

    criado_por BIGINT,

    criado_em TIMESTAMP,

    atualizado_por BIGINT,

    atualizado_em TIMESTAMP

)


