package com.api.rest.inspeçoes_itens.model;

import com.api.rest.inspecao.model.InspecaoEntity;
import com.api.rest.itens.model.ItemEntity;
import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "inspecoes_itens")
public class InspecaoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecao_id")
    private InspecaoEntity inspecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Enumerated(EnumType.STRING)
    private StatusItemEnum status;

    private String observacao;

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
