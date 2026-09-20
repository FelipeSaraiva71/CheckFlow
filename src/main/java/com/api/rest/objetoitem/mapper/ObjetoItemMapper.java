package com.api.rest.objetoitem.mapper;

import com.api.rest.objetoitem.dto.ObjetoItemDtoCreate;
import com.api.rest.objetoitem.dto.ObjetoItemDtoRead;
import com.api.rest.objetoitem.dto.ObjetoItemDtoUpdate;
import com.api.rest.objetoitem.model.ObjetoItemEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ObjetoItemMapper {

    ObjetoItemEntity objetoItemCreateEntity(ObjetoItemDtoCreate objetoItemDtoCreate);

    ObjetoItemEntity objetoItemUpdateEntity(ObjetoItemDtoUpdate objetoItemDtoUpdate);

    ObjetoItemDtoRead objetoItemReadEntity(ObjetoItemEntity objetoItemEntity);
}
