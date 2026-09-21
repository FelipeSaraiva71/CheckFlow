package com.api.rest.usuarios.dto;


import com.api.rest.usuarios.model.StatusUsuarioEnum;
import com.api.rest.usuarios.model.UsuarioTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UsuarioDtoRead {

    private Long id;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String email;

    private StatusUsuarioEnum status;

    private UsuarioTipoEnum tipo;

    private String tenantNome;

}
