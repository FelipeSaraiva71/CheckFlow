package com.api.rest.objetoitem.dto;

import com.api.rest.itens.dto.ItemDtoCreate;
import com.api.rest.objeto.dto.ObjetoDtoCreate;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ObjetoItemDtoCreate {

    @NotNull
    private ObjetoDtoCreate  objeto;

    @NotNull
    private List<ItemDtoCreate> item;
}
