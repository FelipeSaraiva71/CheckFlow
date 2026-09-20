package com.api.rest.itens.mapper;

import com.api.rest.itens.dto.ItemDtoCreate;
import com.api.rest.itens.dto.ItemDtoRead;
import com.api.rest.itens.dto.ItemDtoUpdate;
import com.api.rest.itens.model.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemEntity itemCreateEntity(ItemDtoCreate itemDtoCreate);

    ItemEntity itemUpdateEntity(ItemDtoUpdate itemDtoUpdate);

    ItemDtoRead itemDtoRead(ItemEntity itemEntity);

}
