package com.api.rest.objeto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor

@Builder
public class ObjetoDtoRead {

    private String nome;

    private String identificacao;

    private String descricao;

}
