package com.api.rest.tenant.dto;

import com.api.rest.tenant.model.StatusTenantEnum;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

public class TenantDtoUpdate {

    @NotBlank
    @Size(min = 2, max = 200)
    private String nome;

    @Email
    @NotBlank
    @Size(max = 150)
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}")
    private String telefone;

    @NotBlank
    @Size(max = 300)
    private String endereco;

    @NotNull
    private StatusTenantEnum status;

}
