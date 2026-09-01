package com.api.rest.tenant.model;


import jakarta.persistence.*;

@Table(name = "tenant")
@Entity
public class TenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tenantId;

    private String tenantName;

    private StatusTenantEnum status;

}
