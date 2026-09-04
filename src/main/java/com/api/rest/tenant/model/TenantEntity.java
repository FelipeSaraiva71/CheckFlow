package com.api.rest.tenant.model;


import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "tenant")
@Entity
public class TenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int tenantId;

    private String tenantName;

    private StatusTenantEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por")
    private UsuarioEntity criadoPor;

    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atualizado_por")
    private UsuarioEntity atualizadoPor;

    private LocalDateTime atualizadoEm;

}
