package com.api.rest.objetoitem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ObjetoItemDtoRead {

    private Long objetoId;

    private Long itemId;

}
