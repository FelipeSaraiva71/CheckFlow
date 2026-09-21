package com.api.rest.usuarios.mapper;


import com.api.rest.usuarios.dto.UsuarioAdmDtoCreate;
import com.api.rest.usuarios.dto.UsuarioAdmDtoUpdate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.dto.UsuarioDtoUpdate;
import com.api.rest.usuarios.model.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity createUsuarioAdmEntity(UsuarioAdmDtoCreate usuarioAdmDtoCreate);

    UsuarioEntity updateUsuarioAdmEntity(UsuarioAdmDtoUpdate usuarioAdmDtoUpdate);

    UsuarioEntity createUsuarioEntity(UsuarioAdmDtoCreate usuarioDtoCreate);


    UsuarioEntity updateUsuarioEntity(UsuarioDtoUpdate usuarioDtoUpdate);


    @Mapping(source = "tenant.nome", target = "nomeTenant")
    UsuarioDtoRead readUsuarioDto(UsuarioEntity usuarioEntity);
}
