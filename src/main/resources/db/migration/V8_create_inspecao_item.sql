CREATE TABLE inspecoes_itens
(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    inspecao_id BIGINT NOT NULL ,

    item_id BIGINT NOT NULL ,

    status VARCHAR (10) NOT NULL ,

    observacao VARCHAR (200) ,

    criado_por BIGINT NOT NULL ,

    criado_em TIMESTAMP NOT NULL,

    atualizado_por BIGINT ,

    atualizado_em TIMESTAMP,

    tenant_id BIGINT

)