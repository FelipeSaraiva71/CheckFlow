package com.api.rest.tenant.dto;


import com.api.rest.tenant.model.StatusTenantEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TenantDtoRead {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String endereco;

    private StatusTenantEnum status;
}
