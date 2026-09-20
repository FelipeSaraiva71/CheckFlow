package com.api.rest.inspecao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class InspecaoDtoCreate {

    @NotBlank
    private Long objetoId;

}
