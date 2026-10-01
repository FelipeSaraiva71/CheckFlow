package com.api.rest.inspecoesitens.mapper;

import com.api.rest.inspecao.mapper.InspecaoMapper;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoCreate;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoRead;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoUpdate;
import com.api.rest.inspecoesitens.model.InspecaoItemEntity;
import com.api.rest.itens.mapper.ItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                InspecaoMapper.class,
                ItemMapper.class
        })

public interface InspecaoItemMapper {

    InspecaoItemEntity inspecaoItemCreateEntity(InspecaoItemDtoCreate inspecaoItensDtoCreate);

    InspecaoItemEntity inspecaoItemUpdateEntity(InspecaoItemDtoUpdate inspecaoItensDtoUpdate);

    InspecaoItemDtoRead inspecaoItemReadEntity(InspecaoItemEntity inspecaoItemEntity);
}
