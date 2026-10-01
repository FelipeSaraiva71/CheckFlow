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

    public UsuarioDtoRead saveComum(UsuarioDtoCreate usuarioDtoCreate) {

        if (usuarioRepository.existsByEmail(usuarioDtoCreate.getEmail())) {
            throw new ConflitoException("E-mail já existe!");
        }

        UsuarioEntity usuario = usuarioRepository.findById(2L)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado"));


        TenantEntity tenant = tenantRepository.findById(2L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsavel não encontrado!"));

        UsuarioEntity entity = usuarioMapper.createUsuarioEntity(usuarioDtoCreate);
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

    public UsuarioDtoRead updateComum(Long id, UsuarioDtoUpdate usuarioDtoUpdate) {

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));


        if (usuarioRepository.existsByEmailAndIdNot(usuarioDtoUpdate.getEmail(), id)) {
            throw new ConflitoException("E-mail já existe!");
        }

        usuarioEntity.setNome(usuarioDtoUpdate.getNome());

        usuarioEntity.setSobrenome(usuarioDtoUpdate.getSobrenome());

        usuarioEntity.setTelefone(usuarioDtoUpdate.getTelefone());

        usuarioEntity.setEmail(usuarioDtoUpdate.getEmail());

        usuarioEntity.setStatus(usuarioDtoUpdate.getStatus());

        TenantEntity tenant = tenantRepository.findById(2L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsavel não encontrado!"));
        usuarioEntity.setResponsavel(tenant);


        UsuarioDtoRead usuarioDtoRead = usuarioMapper.readUsuarioDto(usuarioRepository.save(usuarioEntity));

        return usuarioDtoRead;

    }
}
