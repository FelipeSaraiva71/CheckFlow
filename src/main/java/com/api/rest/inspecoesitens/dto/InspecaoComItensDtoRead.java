package com.api.rest.inspecoesitens.dto;

import com.api.rest.inspecao.dto.InspecaoDtoRead;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class InspecaoComItensDtoRead {

    private InspecaoDtoRead inspecao;
    private List<InspecaoItemDtoRead> item;
}