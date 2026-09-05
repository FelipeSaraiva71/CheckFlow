package com.api.rest.usuarios.model;


import com.api.rest.tenant.model.TenantEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table (name = "usuarios")
@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private StatusUsuarioEnum status;

    @Enumerated(EnumType.STRING)
    private UsuarioTipoEnum tipo;

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
    private TenantEntity  tenant;


}
