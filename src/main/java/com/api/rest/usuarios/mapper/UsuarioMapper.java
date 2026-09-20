package com.api.rest.usuarios.mapper;


import com.api.rest.usuarios.dto.UsuarioDtoCreate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.dto.UsuarioDtoUpdate;
import com.api.rest.usuarios.model.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity createUsuarioEntity(UsuarioDtoCreate usuarioDtoCreate);

    UsuarioEntity updateUsuarioEntity(UsuarioDtoUpdate usuarioDtoUpdate);

    UsuarioDtoRead readUsuarioDto(UsuarioEntity usuarioEntity);
}
