package com.api.rest.itens.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ItemDtoRead {

    private Long id;

    private String nome;
}
