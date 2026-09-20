package com.api.rest.itens.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ItemDtoCreate {

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;


}
