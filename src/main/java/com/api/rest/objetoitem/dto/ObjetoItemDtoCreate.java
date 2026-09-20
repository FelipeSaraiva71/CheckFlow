package com.api.rest.objetoitem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ObjetoItemDtoCreate {

    @NotNull
    private Long objetoId;

    @NotNull
    private Long itemId;
}
