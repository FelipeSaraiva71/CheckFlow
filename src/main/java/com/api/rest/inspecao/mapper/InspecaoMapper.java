package com.api.rest.inspecao.mapper;

import com.api.rest.inspecao.dto.InspecaoDtoCreate;
import com.api.rest.inspecao.dto.InspecaoDtoRead;
import com.api.rest.inspecao.dto.InspecaoDtoUpdate;
import com.api.rest.inspecao.model.InspecaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InspecaoMapper {


    InspecaoEntity inspecaoCreateEntity(InspecaoDtoCreate inspecaoDtoCreate);

    InspecaoEntity inspecaoUpdateEntity(InspecaoDtoUpdate inspecaoDtoUpdate);

    InspecaoDtoRead inspecaoReadEntity(InspecaoEntity inspecaoEntity);

}
