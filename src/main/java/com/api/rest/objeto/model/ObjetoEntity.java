package com.api.rest.objeto.model;


import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "objetos")
@Entity

@Getter
@Setter

@NoArgsConstructor

public class ObjetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false , length = 100)
    private String nome;

    @Column(unique = true, nullable = false , length = 100)
    private String identificacao;

    @Column(nullable = false , length = 100)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por")
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
