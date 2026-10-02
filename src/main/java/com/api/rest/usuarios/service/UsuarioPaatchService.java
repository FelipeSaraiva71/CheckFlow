package com.api.rest.usuarios.service;


import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.dto.UsuarioPasswordDtoPatch;
import com.api.rest.usuarios.dto.UsuarioStatusDtoPatch;
import com.api.rest.usuarios.mapper.UsuarioMapper;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioPaatchService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioPaatchService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){

        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }



    @Transactional
    public ResponseEntity<Void> patchPassword(Long id, UsuarioPasswordDtoPatch usuarioPasswordDtoPatch){
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));

         usuarioMapper.patchPasswordEntity(usuarioPasswordDtoPatch, usuarioEntity);

        usuarioEntity.setAtualizadoPor(usuarioEntity);
        usuarioEntity.setAtualizadoEm(LocalDateTime.now());

        usuarioRepository.save(usuarioEntity);

        return   ResponseEntity.noContent().build();
    }

    @Transactional
    public ResponseEntity<UsuarioDtoRead> patchStatus(Long id, UsuarioStatusDtoPatch usuarioStatusDtoPatch){

        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));

         usuarioMapper.patchStatusEntity(usuarioStatusDtoPatch, usuarioEntity);

        usuarioEntity.setAtualizadoPor(usuarioEntity);
        usuarioEntity.setAtualizadoEm(LocalDateTime.now());

         UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

        return   ResponseEntity.ok(usuarioMapper.readUsuarioDto(usuarioSalvo));
    }


}
