CREATE TABLE inspecoes
(

    id             BIGINT AUTO_INCREMENT PRIMARY KEY,

    objeto_id      BIGINT    NOT NULL,

    status VARCHAR(15) NOT NULL,

    criado_por     BIGINT    NOT NULL,

    criado_em      TIMESTAMP NOT NULL,

    atualizado_por BIGINT,

    atualizado_em  TIMESTAMP,

    responsavel_id      BIGINT    NOT NULL,

    CONSTRAINT fk_inspecao_objeto
        FOREIGN KEY (objeto_id)
            REFERENCES objetos (id),

    CONSTRAINT fk_inspecao_criado_por
        FOREIGN KEY (criado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_inspecao_atualizado_por
        FOREIGN KEY (atualizado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_inspecao_tenant
        FOREIGN KEY (responsavel_id)
            REFERENCES tenants (id)
)