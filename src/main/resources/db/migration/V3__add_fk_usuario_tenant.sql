ALTER TABLE tenants

  ADD  CONSTRAINT tenant_fk_usuario_criado_por
    FOREIGN KEY (criado_por)
    REFERENCES usuarios (id);

ALTER TABLE tenants

  ADD  CONSTRAINT tenant_fk_usuario_atualizado_por
    FOREIGN KEY (atualizado_por)
    REFERENCES usuarios (id);