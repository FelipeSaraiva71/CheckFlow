package com.api.rest.inspecao.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor

@Builder
public class InspecaoDtoRead {

    private Long objetoId;
}
