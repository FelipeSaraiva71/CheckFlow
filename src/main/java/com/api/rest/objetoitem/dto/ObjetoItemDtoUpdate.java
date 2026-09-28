package com.api.rest.objetoitem.dto;

import com.api.rest.itens.dto.ItemDtoCreate;
import com.api.rest.itens.dto.ItemDtoUpdate;
import com.api.rest.objeto.dto.ObjetoDtoCreate;
import com.api.rest.objeto.dto.ObjetoDtoUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ObjetoItemDtoUpdate {


    @NotNull
    private Long objetoId;

    @NotNull
    private ObjetoDtoUpdate objeto;

    @NotNull
    private List<ItemDtoUpdate> Item;
}
