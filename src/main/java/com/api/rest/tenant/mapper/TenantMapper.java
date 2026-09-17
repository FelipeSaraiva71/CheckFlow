package com.api.rest.tenant.mapper;
import com.api.rest.tenant.dto.TenantDtoCreate;
import com.api.rest.tenant.dto.TenantDtoRead;
import com.api.rest.tenant.dto.TenantDtoUpdate;
import com.api.rest.tenant.model.TenantEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface TenantMapper {

    TenantEntity createTenantEntity (TenantDtoCreate tenantDtoCreate);

    TenantEntity updateTenantEntity  (TenantDtoUpdate tenantDtoUpdate);

    TenantDtoRead readTenantDto (TenantEntity tenantEntity);



}
