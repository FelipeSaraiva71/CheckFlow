CREATE TABLE itens
(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome VARCHAR (100) NOT NULL ,

    criado_por BIGINT ,

    criado_em TIMESTAMP NOT NULL,

    atualizado_por BIGINT,

    atualizado_em TIMESTAMP,

    tenant_id   BIGINT


)