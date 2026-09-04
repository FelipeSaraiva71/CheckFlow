package com.api.rest.itens.model;

import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "itens")
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


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
