package com.api.rest.usuarios.service;

import com.api.rest.usuarios.dto.UsuarioDtoCreate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.mapper.UsuarioMapper;
import com.api.rest.usuarios.model.StatusUsuarioEnum;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.model.UsuarioTipoEnum;
import com.api.rest.usuarios.repository.UsuarioRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
public class UsuarioAdmService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioAdmService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDtoRead saveAdm(UsuarioDtoCreate usuarioDtoCreate) {

        if (usuarioRepository.existsByEmail(usuarioDtoCreate.getEmail())) {
            throw new IllegalArgumentException("E-mail já existe!");
        }

        UsuarioEntity entity = usuarioMapper.createUsuarioEntity(usuarioDtoCreate);
        entity.setTipo(UsuarioTipoEnum.ADM);
        entity.setStatus(StatusUsuarioEnum.ATIVO);

        UsuarioEntity salvo = usuarioRepository.save(entity);
        return usuarioMapper.readUsuarioDto(salvo);

    }

    public Page<UsuarioDtoRead> findByAllAdm(Pageable pageable) {
        return usuarioRepository.findByTipo(UsuarioTipoEnum.ADM, pageable).map(usuarioMapper::readUsuarioDto);
    }




}
