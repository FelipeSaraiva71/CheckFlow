package com.api.rest.inspeçoesitens.model;

import com.api.rest.inspecao.model.InspecaoEntity;
import com.api.rest.itens.model.ItemEntity;
import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "inspecoes_itens")
@Entity
public class InspecaoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "inspecao_id")
    private InspecaoEntity inspecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "item_id")
    private ItemEntity item;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StatusItemEnum status;

    @Column( length = 200)
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "criado_por")
    private UsuarioEntity criadoPor;

    @Column(nullable = false, name = "criado_em")
    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atualizado_por")
    private UsuarioEntity atualizadoPor;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "tenant_id")
    private TenantEntity tenant;
}
