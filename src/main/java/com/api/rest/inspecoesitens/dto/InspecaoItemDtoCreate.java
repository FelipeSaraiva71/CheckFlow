package com.api.rest.inspecoesitens.dto;

import com.api.rest.inspecoesitens.model.StatusItemEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class InspecaoItemDtoCreate {

    @NotNull
    private Long inspecaoId;

    @NotNull
    private Long itemId;

    @NotNull
    @Size(max = 10)
    private StatusItemEnum status;

    @Size(max = 200)
    private String observacao;

}
