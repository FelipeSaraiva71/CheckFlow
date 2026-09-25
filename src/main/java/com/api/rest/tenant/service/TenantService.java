package com.api.rest.tenant.service;


import com.api.rest.exception.ConflitoException;
import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.tenant.dto.TenantDtoCreate;
import com.api.rest.tenant.dto.TenantDtoRead;
import com.api.rest.tenant.dto.TenantDtoUpdate;
import com.api.rest.tenant.mapper.TenantMapper;
import com.api.rest.tenant.model.StatusTenantEnum;
import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.tenant.repository.TenantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    public TenantService(TenantRepository tenantRepository, TenantMapper tenantMapper) {

        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }

    public TenantDtoRead saveTenant(TenantDtoCreate tenantDtoCreate) {

        if (tenantRepository.existsByNome(tenantDtoCreate.getNome())) {
            throw new ConflitoException("Nome já existe!");
        }

        TenantEntity entity = tenantMapper.createTenantEntity(tenantDtoCreate);
        entity.setStatus(StatusTenantEnum.ATIVO);


        TenantEntity salvo = tenantRepository.save(entity);
        return tenantMapper.readTenantDto(salvo);
    }

    public Page<TenantDtoRead> findByAll(Pageable pageable) {
        return tenantRepository.findAll(pageable).map(tenantMapper::readTenantDto);

    }

    public TenantDtoRead update(Long id, TenantDtoUpdate tenantDtoUpdate) {

        TenantEntity tenantEntity = tenantRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Nome não encontrado!"));

        if (tenantRepository.existsByNome(tenantDtoUpdate.getNome())) {
            throw new ConflitoException("Nome já existe!");
        }

        tenantEntity.setNome(tenantDtoUpdate.getNome());

        tenantEntity.setEmail(tenantDtoUpdate.getEmail());

        tenantEntity.setTelefone(tenantDtoUpdate.getTelefone());

        tenantEntity.setEndereco(tenantDtoUpdate.getEndereco());

        tenantEntity.setStatus(tenantDtoUpdate.getStatus());

        TenantDtoRead tenantDtoRead = tenantMapper.readTenantDto(tenantRepository.save(tenantEntity));

        return tenantDtoRead;


    }
}