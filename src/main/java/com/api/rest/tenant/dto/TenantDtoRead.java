package com.api.rest.tenant.dto;


import com.api.rest.tenant.model.StatusTenantEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

public class TenantDtoRead {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String endereco;

    private StatusTenantEnum status;
}
