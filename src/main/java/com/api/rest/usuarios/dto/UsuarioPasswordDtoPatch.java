package com.api.rest.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class UsuarioPasswordDtoPatch {

    @NotBlank
    @Size(min = 4, max = 60)
    private String password;
}
