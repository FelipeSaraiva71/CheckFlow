package com.api.rest.inspeçoes_itens.model;

import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "inspecoes_itens")
public class InspecaoItemEntity {




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por")
    private UsuarioEntity criadoPor;

    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atualizado_por")
    private UsuarioEntity atualizadoPor;

    private LocalDateTime atualizadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private TenantEntity tenant;
}
