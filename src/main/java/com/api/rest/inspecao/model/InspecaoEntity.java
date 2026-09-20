package com.api.rest.inspecao.model;


import com.api.rest.objeto.model.ObjetoEntity;
import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table (name = "inspecoes")
@Entity

@Getter
@Setter
@NoArgsConstructor

public class InspecaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (nullable = false, name = "objeto_id")
    private ObjetoEntity objeto;

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
