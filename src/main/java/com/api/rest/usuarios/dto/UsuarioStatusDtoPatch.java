package com.api.rest.usuarios.dto;

import com.api.rest.usuarios.model.StatusUsuarioEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class UsuarioStatusDtoPatch {

    @NotNull
    private StatusUsuarioEnum status;
}
