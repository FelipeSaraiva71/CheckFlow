CREATE TABLE inspecoes_itens
(

    id             BIGINT AUTO_INCREMENT PRIMARY KEY,

    inspecao_id    BIGINT      NOT NULL,

    item_id        BIGINT      NOT NULL,

    status         VARCHAR(10) NOT NULL,

    observacao     VARCHAR(200),

    criado_por     BIGINT      NOT NULL,

    criado_em      TIMESTAMP   NOT NULL,

    atualizado_por BIGINT,

    atualizado_em  TIMESTAMP,

    tenant_id      BIGINT      NOT NULL,


    CONSTRAINT fk_inspecao_itens_inspecao
        FOREIGN KEY (inspecao_id)
            REFERENCES inspecoes (id),

    CONSTRAINT fk_inspecao_item
        FOREIGN KEY (item_id)
            REFERENCES itens (id),

    CONSTRAINT fk_inspecao_item_criado_por
        FOREIGN KEY (criado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_inspecao_atualizado_por
        FOREIGN KEY (atualizado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_inspecao_item_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenants (id)

)