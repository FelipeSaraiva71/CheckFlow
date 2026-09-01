package com.api.rest.usuarios.model;


import com.api.rest.tenant.model.StatusTenantEnum;
import com.api.rest.tenant.model.TenantEntity;
import jakarta.persistence.*;

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

    private StatusTenantEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private TenantEntity  tenant;


}
