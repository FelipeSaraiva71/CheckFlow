package com.api.rest.itens.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ItemDtoCreate {

    @NotBlank
    @Column(unique = true, nullable = false, length = 100)
    private String nome;


}
