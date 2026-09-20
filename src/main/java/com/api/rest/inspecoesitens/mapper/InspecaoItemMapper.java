package com.api.rest.inspecoesitens.mapper;

import com.api.rest.inspecoesitens.dto.InspecaoItemDtoCreate;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoRead;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoUpdate;
import com.api.rest.inspecoesitens.model.InspecaoItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InspecaoItemMapper {

    InspecaoItemEntity inspecaoItemCreateEntity(InspecaoItemDtoCreate inspecaoItensDtoCreate);

    InspecaoItemEntity inspecaoItemUpdateEntity(InspecaoItemDtoUpdate inspecaoItensDtoUpdate);

    InspecaoItemDtoRead inspecaoItemReadEntity(InspecaoItemEntity inspecaoItemEntity);
}
