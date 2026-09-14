CREATE TABLE objetos_itens
(

    id             BIGINT AUTO_INCREMENT PRIMARY KEY,

    objeto_id      BIGINT    NOT NULL,

    item_id        BIGINT    NOT NULL,

    criado_por     BIGINT    NOT NULL,

    criado_em      TIMESTAMP NOT NULL,

    atualizado_por BIGINT,

    atualizado_em  TIMESTAMP,

    tenant_id      BIGINT    NOT NULL,

    CONSTRAINT fk_objeto
        FOREIGN KEY (objeto_id)
            REFERENCES objetos (id),

    CONSTRAINT fk_item
        FOREIGN KEY (item_id)
            REFERENCES itens (id),

    CONSTRAINT fk_criado_por
        FOREIGN KEY (criado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_atualizado_por
        FOREIGN KEY (atualizado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenant (id)


)