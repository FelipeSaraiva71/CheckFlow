package com.api.rest.objeto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ObjetoDtoCreate {

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 100)
    private String identificacao;

    @NotBlank
    @Size(min = 1, max = 100)
    private String descricao;




}
