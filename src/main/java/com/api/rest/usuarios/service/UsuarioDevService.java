package com.api.rest.usuarios.service;

import com.api.rest.exception.ConflitoException;
import com.api.rest.exception.RecursoNaoEncontradoException;
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

import java.time.LocalDateTime;

@Service
public class UsuarioDevService {

    private final UsuarioRepository usuarioRepository;
    private final TenantRepository tenantRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioDevService(UsuarioRepository usuarioRepository, TenantRepository tenantRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.tenantRepository = tenantRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDtoRead saveDev(UsuarioAdmDtoCreate usuarioAdmDtoCreate) {

        if (usuarioRepository.existsByEmail(usuarioAdmDtoCreate.getEmail())) {
            throw new ConflitoException("E-mail já existe!");
        }

        UsuarioEntity usuario = usuarioRepository.findById(1L)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado"));


        TenantEntity tenant = tenantRepository.findById(usuarioAdmDtoCreate.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsavel não encontrado!"));

        UsuarioEntity entity = usuarioMapper.createUsuarioAdmEntity(usuarioAdmDtoCreate);
        entity.setTipo(UsuarioTipoEnum.DEV);
        entity.setStatus(StatusUsuarioEnum.ATIVO);
        entity.setResponsavel(tenant);
        entity.setCriadoPor(usuario);
        entity.setCriadoEm(LocalDateTime.now());

        UsuarioEntity salvo = usuarioRepository.save(entity);
        return usuarioMapper.readUsuarioDto(salvo);

    }

    public Page<UsuarioDtoRead> findByAllDev(Pageable pageable) {
        return usuarioRepository.findByTipo(UsuarioTipoEnum.DEV, pageable).map(usuarioMapper::readUsuarioDto);
    }

    public UsuarioDtoRead updateDev(Long id, UsuarioAdmDtoUpdate usuarioAdmDtoUpdate) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));


        if (usuarioRepository.existsByEmailAndIdNot(usuarioAdmDtoUpdate.getEmail(), id)) {
            throw new ConflitoException("E-mail já existe!");
        }

        usuarioEntity.setNome(usuarioAdmDtoUpdate.getNome());

        usuarioEntity.setSobrenome(usuarioAdmDtoUpdate.getSobrenome());

        usuarioEntity.setTelefone(usuarioAdmDtoUpdate.getTelefone());

        usuarioEntity.setEmail(usuarioAdmDtoUpdate.getEmail());

        usuarioEntity.setStatus(usuarioAdmDtoUpdate.getStatus());

        TenantEntity tenant = tenantRepository.findById(usuarioAdmDtoUpdate.getResponsavelId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsavel não encontrado!"));
        usuarioEntity.setResponsavel(tenant);


        UsuarioDtoRead usuarioDtoRead = usuarioMapper.readUsuarioDto(usuarioRepository.save(usuarioEntity));

        return usuarioDtoRead;

    }
}
