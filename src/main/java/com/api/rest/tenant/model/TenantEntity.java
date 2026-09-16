package com.api.rest.tenant.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String nome;

    @Column(length = 150)
    private String email;

    @Column(length = 11)
    private String telefone;

    @Column(length = 300)
    private String endereco;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private StatusTenantEnum status;

}
