package com.api.rest.objetoitem.mapper;

import com.api.rest.itens.dto.ItemDtoRead;
import com.api.rest.objeto.dto.ObjetoDtoRead;
import com.api.rest.objetoitem.dto.ObjetoItemDtoRead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjetoItemMapper {


    @Mapping(target = "objeto", source = "objeto")
    @Mapping(target = "item", source = "item")
    ObjetoItemDtoRead objetoItemReadEntity(ObjetoDtoRead objeto, List<ItemDtoRead> item);
}
