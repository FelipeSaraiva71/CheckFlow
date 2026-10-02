package com.api.rest.usuarios.service;

import com.api.rest.exception.ConflitoException;
import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.tenant.model.TenantEntity;
import com.api.rest.tenant.repository.TenantRepository;
import com.api.rest.usuarios.dto.*;
import com.api.rest.usuarios.mapper.UsuarioMapper;
import com.api.rest.usuarios.model.StatusUsuarioEnum;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.model.UsuarioTipoEnum;
import com.api.rest.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioComumService {

    private final UsuarioRepository usuarioRepository;
    private final TenantRepository tenantRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioComumService(UsuarioRepository usuarioRepository, TenantRepository tenantRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.tenantRepository = tenantRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDtoRead saveComum(UsuarioComumDtoCreate usuarioComumDtoCreate) {

        if (usuarioRepository.existsByEmail(usuarioComumDtoCreate.getEmail())) {
            throw new ConflitoException("E-mail já existe!");
        }

        UsuarioEntity usuario = usuarioRepository.findById(2L)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado"));


        TenantEntity tenant = tenantRepository.findById(2L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsavel não encontrado!"));

        UsuarioEntity entity = usuarioMapper.createUsuarioEntity(usuarioComumDtoCreate);

        entity.setTipo(UsuarioTipoEnum.COMUM);
        entity.setStatus(StatusUsuarioEnum.ATIVO);
        entity.setResponsavel(tenant);
        entity.setCriadoPor(usuario);
        entity.setCriadoEm(LocalDateTime.now());

        UsuarioEntity salvo = usuarioRepository.save(entity);
        return usuarioMapper.readUsuarioDto(salvo);

    }

    public Page<UsuarioDtoRead> findByAllComum(Pageable pageable) {
        return usuarioRepository.findByTipo(UsuarioTipoEnum.COMUM, pageable).map(usuarioMapper::readUsuarioDto);
    }

    @Transactional
    public UsuarioDtoRead updateComum(Long id, UsuarioComumDtoUpdate usuarioComumDtoUpdate) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));

        if (usuarioRepository.existsByEmailAndIdNot(usuarioComumDtoUpdate.getEmail(), id)) {
            throw new ConflitoException("E-mail já existe!");
        }

        usuarioMapper.updateUsuarioEntity(usuarioComumDtoUpdate, usuarioEntity);

        usuarioEntity.setAtualizadoPor(usuarioEntity);
        usuarioEntity.setAtualizadoEm(LocalDateTime.now());

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

        return usuarioMapper.readUsuarioDto(usuarioSalvo);
    }
}
