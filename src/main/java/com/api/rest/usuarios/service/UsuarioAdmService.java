package com.api.rest.usuarios.service;

import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.tenant.repository.TenantRepository;
import com.api.rest.usuarios.dto.UsuarioAdmDtoCreate;
import com.api.rest.usuarios.dto.UsuarioAdmDtoUpdate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.mapper.UsuarioMapper;
import com.api.rest.usuarios.model.StatusUsuarioEnum;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.model.UsuarioTipoEnum;
import com.api.rest.usuarios.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAdmService {

    private final UsuarioRepository usuarioRepository;
    private final TenantRepository tenantRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioAdmService(UsuarioRepository usuarioRepository, TenantRepository tenantRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.tenantRepository = tenantRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDtoRead saveAdm(UsuarioAdmDtoCreate usuarioAdmDtoCreate) {

        if (usuarioRepository.existsByEmail(usuarioAdmDtoCreate.getEmail())) {
            throw new IllegalArgumentException("E-mail já existe!");
        }

        UsuarioEntity entity = usuarioMapper.createUsuarioAdmEntity(usuarioAdmDtoCreate);
        entity.setTipo(UsuarioTipoEnum.ADM);
        entity.setStatus(StatusUsuarioEnum.ATIVO);

        UsuarioEntity salvo = usuarioRepository.save(entity);
        return usuarioMapper.readUsuarioDto(salvo);

    }

    public Page<UsuarioDtoRead> findByAllAdm(Pageable pageable) {
        return usuarioRepository.findByTipo(UsuarioTipoEnum.ADM, pageable).map(usuarioMapper::readUsuarioDto);
    }

    public UsuarioDtoRead updateAdm(Long id, UsuarioAdmDtoUpdate usuarioAdmDtoUpdate) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado!"));


        if (usuarioRepository.existsByEmailAndIdNot(usuarioAdmDtoUpdate.getEmail(), id)) {
            throw new IllegalArgumentException("E-mail já existe!");
        }

        usuarioEntity.setNome(usuarioAdmDtoUpdate.getNome());

        usuarioEntity.setSobrenome(usuarioAdmDtoUpdate.getSobrenome());

        usuarioEntity.setTelefone(usuarioAdmDtoUpdate.getTelefone());

        usuarioEntity.setEmail(usuarioAdmDtoUpdate.getEmail());

        usuarioEntity.setStatus(usuarioAdmDtoUpdate.getStatus());

        TenantEntity tenant = tenantRepository.findById(usuarioAdmDtoUpdate.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("Tenant não encontrado!"));
        usuarioEntity.setTenant(tenant);


        UsuarioDtoRead usuarioDtoRead = usuarioMapper.readUsuarioDto(usuarioRepository.save(usuarioEntity));

        return usuarioDtoRead;

    }
}
