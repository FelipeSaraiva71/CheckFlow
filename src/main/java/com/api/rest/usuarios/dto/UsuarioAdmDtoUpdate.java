package com.api.rest.usuarios.dto;

import com.api.rest.usuarios.model.StatusUsuarioEnum;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder

public class UsuarioAdmDtoUpdate {

    @NotBlank
    @Size(min = 1, max = 20)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 50)
    private String sobrenome;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}")
    private String telefone;

    @NotBlank
    @Email
    @Size(min = 1, max = 150)
    private String email;

    @NotNull
    private StatusUsuarioEnum status;

    @NotNull
    private Long tenantId;
}
