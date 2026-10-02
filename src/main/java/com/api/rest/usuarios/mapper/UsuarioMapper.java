package com.api.rest.usuarios.mapper;


import com.api.rest.usuarios.dto.*;
import com.api.rest.usuarios.model.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity createUsuarioAdmEntity(UsuarioAdmDtoCreate usuarioAdmDtoCreate);

    @Mapping(target = "responsavel", ignore = true)
    @Mapping(target = "atualizadoPor", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    void updateUsuarioAdmEntity(UsuarioAdmDtoUpdate usuarioAdmDtoUpdate, @MappingTarget UsuarioEntity usuarioEntity);

    @Mapping(target = "responsavel", ignore = true)
    @Mapping(target = "atualizadoPor", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    void updateUsuarioDevEntity(UsuarioDevDtoUpdate usuarioDevDtoUpdate, @MappingTarget UsuarioEntity usuarioEntity);

    UsuarioEntity createUsuarioEntity(UsuarioComumDtoCreate usuarioComumDtoCreate);

    @Mapping(target = "responsavel", ignore = true)
    @Mapping(target = "atualizadoPor", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    void updateUsuarioEntity(UsuarioComumDtoUpdate usuarioComumDtoUpdate, @MappingTarget UsuarioEntity usuarioEntity);


    void patchPasswordEntity(UsuarioPasswordDtoPatch usuarioPasswordDtoPatch, @MappingTarget UsuarioEntity usuarioEntity);

    void patchStatusEntity(UsuarioStatusDtoPatch usuarioStatusDtoPatch, @MappingTarget UsuarioEntity usuarioEntity);

    @Mapping(source = "responsavel.responsavel", target = "nomeResponsavel")
    UsuarioDtoRead readUsuarioDto(UsuarioEntity usuarioEntity);
}
