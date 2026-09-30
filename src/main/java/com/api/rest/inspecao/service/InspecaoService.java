package com.api.rest.inspecao.service;

import com.api.rest.exception.ConflitoException;
import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.inspecao.dto.InspecaoDtoCreate;
import com.api.rest.inspecao.dto.InspecaoDtoRead;
import com.api.rest.inspecao.mapper.InspecaoMapper;
import com.api.rest.inspecao.model.InspecaoEntity;
import com.api.rest.inspecao.model.InspecaoStatusEnum;
import com.api.rest.inspecao.repository.InspecaoRepository;
import com.api.rest.inspecoesitens.repository.InspecaoItemRepository;
import com.api.rest.objeto.model.ObjetoEntity;
import com.api.rest.objeto.repository.ObjetoRepository;
import com.api.rest.objetoitem.repository.ObjetoItemRepository;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InspecaoService {

    private final ObjetoItemRepository objetoItemRepository;
    private final InspecaoItemRepository inspecaoItemRepository;
    private final InspecaoRepository inspecaoRepository;
    private final ObjetoRepository objetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final InspecaoMapper inspecaoMapper;

    public InspecaoService(ObjetoItemRepository objetoItemRepository, InspecaoItemRepository inspecaoItemRepository, InspecaoRepository inspecaoRepository, ObjetoRepository objetoRepository, UsuarioRepository usuarioRepository, InspecaoMapper inspecaoMapper) {

        this.objetoItemRepository = objetoItemRepository;
        this.inspecaoItemRepository = inspecaoItemRepository;
        this.inspecaoRepository = inspecaoRepository;
        this.objetoRepository = objetoRepository;
        this.usuarioRepository = usuarioRepository;
        this.inspecaoMapper = inspecaoMapper;

    }


    public InspecaoDtoRead create(InspecaoDtoCreate inspecaoDtoCreate) {

        ObjetoEntity objetoEntity = objetoRepository.findById(inspecaoDtoCreate.getObjetoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Objeto não encontrado!"));

        UsuarioEntity usuarioEntity = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));

        InspecaoEntity inspecaoEntity = inspecaoMapper.inspecaoCreateEntity(inspecaoDtoCreate);

        inspecaoEntity.setObjeto(objetoEntity);
        inspecaoEntity.setStatus(InspecaoStatusEnum.EM_ANDAMENTO);
        inspecaoEntity.setCriadoPor(usuarioEntity);
        inspecaoEntity.setCriadoEm(LocalDateTime.now());
        inspecaoEntity.setResponsavel(usuarioEntity.getResponsavel());
        InspecaoEntity salvo = inspecaoRepository.save(inspecaoEntity);

        return inspecaoMapper.inspecaoReadEntity(salvo);

    }

    @Transactional
    public InspecaoDtoRead patchExecutado(Long id) {

        InspecaoEntity inspecaoEntity = inspecaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Inspeção não está disponível!"));

        if (inspecaoEntity.getStatus() != InspecaoStatusEnum.EM_ANDAMENTO) {
            throw new ConflitoException("A inspeção não está em andamento!");
        }

        long totalItens = objetoItemRepository.countByObjeto(inspecaoEntity.getObjeto());

        long totalItensRespondidos = inspecaoItemRepository.countByInspecao(inspecaoEntity);

        if (totalItens != totalItensRespondidos) {
            throw new ConflitoException("Verifique todos itens antes de finalizar!");
        }

        UsuarioEntity usuarioEntity = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));


        inspecaoEntity.setStatus(InspecaoStatusEnum.EXECUTADO);
        inspecaoEntity.setAtualizadoPor(usuarioEntity);
        inspecaoEntity.setAtualizadoEm(LocalDateTime.now());
        InspecaoEntity salvo = inspecaoRepository.save(inspecaoEntity);

        return inspecaoMapper.inspecaoReadEntity(salvo);

    }

    @Transactional
    public InspecaoDtoRead patchPendente(Long id) {

        InspecaoEntity inspecaoEntity = inspecaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Inspeção não está disponível!"));

        if (inspecaoEntity.getStatus() != InspecaoStatusEnum.EM_ANDAMENTO) {
            throw new ConflitoException("A inspeção não está em andamento!");
        }

        long totalItens = objetoItemRepository.countByObjeto(inspecaoEntity.getObjeto());

        long totalItensRespondidos = inspecaoItemRepository.countByInspecao(inspecaoEntity);

        if (totalItens == totalItensRespondidos) {
            throw new ConflitoException("Todos itens foram vistoriados, favor finalizar!");
        }

        UsuarioEntity usuarioEntity = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado!"));


        inspecaoEntity.setStatus(InspecaoStatusEnum.PENDENTE);
        inspecaoEntity.setAtualizadoPor(usuarioEntity);
        inspecaoEntity.setAtualizadoEm(LocalDateTime.now());
        InspecaoEntity salvo = inspecaoRepository.save(inspecaoEntity);

        return inspecaoMapper.inspecaoReadEntity(salvo);

    }


}
