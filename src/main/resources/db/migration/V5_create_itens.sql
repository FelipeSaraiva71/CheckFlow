CREATE TABLE itens
(

    id             BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome           VARCHAR(100) NOT NULL,

    criado_por     BIGINT       NOT NULL,

    criado_em      TIMESTAMP    NOT NULL,

    atualizado_por BIGINT,

    atualizado_em  TIMESTAMP,

    tenant_id      BIGINT       NOT NULL,

    CONSTRAINT fk_criado_por
        FOREIGN KEY (criado_por)
            REFERENCES usuario (id),

    CONSTRAINT fk_atualizado_por
        FOREIGN KEY (atualizado_em)
            REFERENCES usuario_id,

    CONSTRAINT fk_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenant (id)

)