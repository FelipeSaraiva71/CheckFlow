package com.api.rest.objetoitem.dto;

import com.api.rest.itens.dto.ItemDtoCreate;
import com.api.rest.itens.dto.ItemDtoRead;
import com.api.rest.objeto.dto.ObjetoDtoCreate;
import com.api.rest.objeto.dto.ObjetoDtoRead;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ObjetoItemDtoRead {

    private Long id;

    private ObjetoDtoRead objeto;

    private List<ItemDtoRead> item;

}
