package com.api.rest.inspeçoesitens.dto;

import com.api.rest.inspeçoesitens.model.StatusItemEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter

@AllArgsConstructor

@Builder
public class InspecaoItensDtoRead {

    private Long inspecaoId;

    private Long itemId;

    private StatusItemEnum status;

    private String observacao;
}
