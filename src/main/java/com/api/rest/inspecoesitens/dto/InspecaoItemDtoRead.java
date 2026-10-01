package com.api.rest.inspecoesitens.dto;

import com.api.rest.inspecao.dto.InspecaoDtoRead;
import com.api.rest.inspecoesitens.model.StatusItemEnum;
import com.api.rest.itens.dto.ItemDtoRead;
import com.api.rest.itens.model.ItemEntity;
import com.api.rest.objeto.model.ObjetoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter

@AllArgsConstructor

@Builder
public class InspecaoItemDtoRead {

    private ItemDtoRead item;

    private StatusItemEnum status;

    private String observacao;
}
