package com.api.rest.inspecao.dto;

import com.api.rest.inspecao.model.InspecaoStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

public class InspecaoDtoUpdate {

    @NotBlank
    private Long objetoId;

    @NotNull
    private InspecaoStatusEnum status;

}
