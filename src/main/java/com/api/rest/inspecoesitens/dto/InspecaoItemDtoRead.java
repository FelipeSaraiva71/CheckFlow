package com.api.rest.inspecoesitens.dto;

import com.api.rest.inspecoesitens.model.StatusItemEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter

@AllArgsConstructor

@Builder
public class InspecaoItemDtoRead {

    private Long inspecaoId;

    private Long itemId;

    private StatusItemEnum status;

    private String observacao;
}
