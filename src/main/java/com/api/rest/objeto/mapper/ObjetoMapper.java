package com.api.rest.objeto.mapper;


import com.api.rest.objeto.dto.ObjetoDtoCreate;
import com.api.rest.objeto.dto.ObjetoDtoRead;
import com.api.rest.objeto.dto.ObjetoDtoUpdate;
import com.api.rest.objeto.model.ObjetoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjetoMapper {

    ObjetoEntity createObjetoEntity(ObjetoDtoCreate objetoDtoCreate);

    ObjetoEntity updateObjetoEntity(ObjetoDtoUpdate objetoDtoUpdate);

    ObjetoDtoRead readObjetoDto(ObjetoEntity objetoEntity);
}
