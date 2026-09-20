CREATE TABLE objetos
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome          VARCHAR(100) NOT NULL,

    identificacao  VARCHAR(100) UNIQUE NOT NULL,

    descricao      VARCHAR(100) NOT NULL,

    criado_por     BIGINT NOT NULL ,

    criado_em      TIMESTAMP    NOT NULL,

    atualizado_por BIGINT,

    atualizado_em  TIMESTAMP,

    tenant_id      BIGINT       NOT NULL,

    CONSTRAINT fk_objeto_criado_por
        FOREIGN KEY (criado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_objeto_atualizado_por
        FOREIGN KEY (atualizado_por)
            REFERENCES usuarios (id),

    CONSTRAINT fk_objeto_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenants (id)

)