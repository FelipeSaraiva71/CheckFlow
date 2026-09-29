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
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final UsuarioRepository usuarioRepository;

    public TenantService(TenantRepository tenantRepository, TenantMapper tenantMapper, UsuarioRepository usuarioRepository) {

        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
        this.usuarioRepository = usuarioRepository;
    }

    public TenantDtoRead saveTenant(TenantDtoCreate tenantDtoCreate) {

        if (tenantRepository.existsByResponsavel(tenantDtoCreate.getResponsavel())) {
            throw new ConflitoException("Responsavel já existe!");
        }

        UsuarioEntity usuario = usuarioRepository.findById(1L)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado"));


        TenantEntity entity = tenantMapper.createTenantEntity(tenantDtoCreate);
        entity.setStatus(StatusTenantEnum.ATIVO);
        entity.setCriadoPor(usuario);
        entity.setCriadoEm(LocalDateTime.now());


        TenantEntity salvo = tenantRepository.save(entity);
        return tenantMapper.readTenantDto(salvo);
    }

    public Page<TenantDtoRead> findByAll(Pageable pageable) {
        return tenantRepository.findAll(pageable).map(tenantMapper::readTenantDto);

    }

    public TenantDtoRead update(Long id, TenantDtoUpdate tenantDtoUpdate) {

        TenantEntity tenantEntity = tenantRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Nome não encontrado!"));

        if (tenantRepository.existsByResponsavel(tenantDtoUpdate.getResponsavel())) {
            throw new ConflitoException("Responsavel já existe!");
        }

        tenantEntity.setResponsavel(tenantDtoUpdate.getResponsavel());

        tenantEntity.setEmail(tenantDtoUpdate.getEmail());

        tenantEntity.setTelefone(tenantDtoUpdate.getTelefone());

        tenantEntity.setEndereco(tenantDtoUpdate.getEndereco());

        tenantEntity.setStatus(tenantDtoUpdate.getStatus());

        TenantDtoRead tenantDtoRead = tenantMapper.readTenantDto(tenantRepository.save(tenantEntity));

        return tenantDtoRead;


    }
}