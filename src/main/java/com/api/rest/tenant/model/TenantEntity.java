package com.api.rest.tenant.model;


import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "tenants")
@Entity

@Getter
@Setter
@NoArgsConstructor
public class TenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 200)
    private String responsavel;

    @Column(length = 150)
    private String email;

    @Column(length = 11)
    private String telefone;

    @Column(length = 300)
    private String endereco;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private StatusTenantEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por", nullable = false)
    private UsuarioEntity criadoPor;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atualizado_por")
    private UsuarioEntity atualizadoPor;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

}
