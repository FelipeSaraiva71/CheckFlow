package com.api.rest.tenant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class TenantDtoCreate {

    @NotBlank
    @Size(min = 2, max = 200)
    private String nome;

    @Email
    @Size(max = 150)
    private String email;

    @Pattern(regexp = "\\d{10,11}")
    private String telefone;

    @Size(max = 300)
    private String endereco;
}
