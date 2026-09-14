package com.api.rest.tenant.model;


import com.api.rest.usuarios.model.UsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "tenant")
@Entity
public class TenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 10)
    private StatusTenantEnum status;

}
