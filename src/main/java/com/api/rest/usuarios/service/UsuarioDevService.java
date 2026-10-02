package com.api.rest.usuarios.service;

import com.api.rest.exception.ConflitoException;
import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.usuarios.dto.UsuarioDevDtoUpdate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.mapper.UsuarioMapper;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.model.UsuarioTipoEnum;
import com.api.rest.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioDevService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioDevService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }


    public Page<UsuarioDtoRead> findByAllDev(Pageable pageable) {
        return usuarioRepository.findByTipo(UsuarioTipoEnum.DEV, pageable).map(usuarioMapper::readUsuarioDto);
    }

    @Transactional
    public UsuarioDtoRead updateDev(Long id, UsuarioDevDtoUpdate usuarioDevDtoUpdate) {

        if (usuarioRepository.existsByEmailAndIdNot(usuarioDevDtoUpdate.getEmail(), id)) {
            throw new ConflitoException("E-mail já existe!");
        }
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuario não encontrado!"));

        usuarioMapper.updateUsuarioDevEntity(usuarioDevDtoUpdate, usuarioEntity);

        usuarioEntity.setAtualizadoPor(usuarioEntity);
        usuarioEntity.setAtualizadoEm(LocalDateTime.now());

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

        return usuarioMapper.readUsuarioDto(usuarioSalvo);

    }
}
