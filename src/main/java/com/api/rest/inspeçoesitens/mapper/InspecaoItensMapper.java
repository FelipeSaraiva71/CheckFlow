package com.api.rest.inspeçoesitens.mapper;

import com.api.rest.inspeçoesitens.dto.InspecaoItensDtoCreate;
import com.api.rest.inspeçoesitens.dto.InspecaoItensDtoRead;
import com.api.rest.inspeçoesitens.dto.InspecaoItensDtoUpdate;
import com.api.rest.inspeçoesitens.model.InspecaoItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InspecaoItensMapper {

    InspecaoItemEntity inspecaoItemCreateEntity(InspecaoItensDtoCreate inspecaoItensDtoCreate);

    InspecaoItemEntity inspecaoItemUpdateEntity(InspecaoItensDtoUpdate inspecaoItensDtoUpdate);

    InspecaoItensDtoRead inspecaoItemReadEntity(InspecaoItemEntity inspecaoItemEntity);
}
