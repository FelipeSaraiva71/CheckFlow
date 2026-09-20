package com.api.rest.inspeçoesitens.dto;

import com.api.rest.inspeçoesitens.model.StatusItemEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class InspecaoItensDtoCreate {

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
