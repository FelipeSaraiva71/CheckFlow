CREATE TABLE objetos_itens
(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    objeto_id BIGINT NOT NULL ,

    item_id BIGINT NOT NULL ,

    criado_por BIGINT NOT NULL ,

    criado_em TIMESTAMP NOT NULL,

    atualizado_por BIGINT ,

    atualizado_em TIMESTAMP,

    tenant_id BIGINT

)