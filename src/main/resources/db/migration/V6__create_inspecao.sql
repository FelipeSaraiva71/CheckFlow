CREATE TABLE inspecoes
(

    id             BIGINT AUTO_INCREMENT PRIMARY KEY,

    objeto_id      BIGINT    NOT NULL,

    criado_por     BIGINT    NOT NULL,

    criado_em      TIMESTAMP NOT NULL,

    atualizado_por BIGINT,

    atualizado_em  TIMESTAMP,

    tenant_id      BIGINT    NOT NULL,

    CONSTRAINT fk_inspecao_objeto
        FOREIGN KEY (objeto_id)
            REFERENCES objetos (id),

    CONSTRAINT fk_inspecao_criado_por
        FOREIGN KEY (criado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_inspecao_atualizado_em
        FOREIGN KEY (atualizado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_inspecao_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenants (id)
)