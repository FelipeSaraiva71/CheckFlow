package com.api.rest.usuarios.mapper;


import com.api.rest.usuarios.dto.*;
import com.api.rest.usuarios.model.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity createUsuarioAdmEntity(UsuarioAdmDtoCreate usuarioAdmDtoCreate);

    UsuarioEntity updateUsuarioAdmEntity(UsuarioAdmDtoUpdate usuarioAdmDtoUpdate);

    UsuarioEntity createUsuarioEntity(UsuarioDtoCreate usuarioDtoCreate);


    UsuarioEntity updateUsuarioEntity(UsuarioDtoUpdate usuarioDtoUpdate);


    @Mapping(source = "responsavel.responsavel", target = "nomeResponsavel")
    UsuarioDtoRead readUsuarioDto(UsuarioEntity usuarioEntity);
}
