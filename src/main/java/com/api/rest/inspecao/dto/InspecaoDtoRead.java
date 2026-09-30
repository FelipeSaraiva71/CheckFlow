package com.api.rest.inspecao.dto;


import com.api.rest.inspecao.model.InspecaoStatusEnum;
import com.api.rest.objeto.dto.ObjetoDtoRead;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import tools.jackson.databind.EnumNamingStrategies;

@Getter
@AllArgsConstructor

@Builder
public class InspecaoDtoRead {

    private Long id;
    private ObjetoDtoRead objeto;
    private InspecaoStatusEnum status;
}
