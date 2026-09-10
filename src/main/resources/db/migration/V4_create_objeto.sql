CREATE TABLE objetos
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    tipo VARCHAR (100) NOT NULL ,

    identificacao VARCHAR (100) NOT NULL ,

    descricao VARCHAR (100) NOT NULL ,


    criado_por BIGINT ,

    criado_em TIMESTAMP NOT NULL,

    atualizado_por BIGINT,

    atualizado_em TIMESTAMP,

    tenant_id BIGINT

)