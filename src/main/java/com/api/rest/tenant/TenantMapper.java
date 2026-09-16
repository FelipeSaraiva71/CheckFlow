package com.api.rest.tenant;
import com.api.rest.tenant.dto.TenantDtoCreate;
import com.api.rest.tenant.dto.TenantDtoRead;
import com.api.rest.tenant.dto.TenantDtoUpdate;
import com.api.rest.tenant.model.TenantEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface TenantMapper {

    TenantEntity createEntity (TenantDtoCreate tenantDtoCreate);

    TenantEntity updateEntity  (TenantDtoUpdate tenantDtoUpdate);

    TenantDtoRead readDto (TenantEntity tenantEntity);



}
