package com.api.rest.inspecoesitens.service;

import com.api.rest.exception.ConflitoException;
import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.inspecao.model.InspecaoEntity;
import com.api.rest.inspecao.model.InspecaoStatusEnum;
import com.api.rest.inspecao.repository.InspecaoRepository;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoCreate;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoRead;
import com.api.rest.inspecoesitens.mapper.InspecaoItemMapper;
import com.api.rest.inspecoesitens.model.InspecaoItemEntity;
import com.api.rest.inspecoesitens.model.StatusItemEnum;
import com.api.rest.inspecoesitens.repository.InspecaoItemRepository;
import com.api.rest.itens.model.ItemEntity;
import com.api.rest.itens.repository.ItemRepository;
import com.api.rest.objetoitem.repository.ObjetoItemRepository;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InspecaoItensService {

    private final InspecaoItemRepository inspecaoItemRepository;
    private final InspecaoRepository inspecaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ItemRepository itemRepository;
    private final ObjetoItemRepository objetoItemRepository;
    private final InspecaoItemMapper inspecaoItemMapper;

    public InspecaoItensService(InspecaoItemRepository inspecaoItemRepository, InspecaoRepository inspecaoRepository, UsuarioRepository usuarioRepository, ItemRepository itemRepository, ObjetoItemRepository objetoItemRepository, InspecaoItemMapper inspecaoItemMapper) {

        this.inspecaoItemRepository = inspecaoItemRepository;
        this.inspecaoRepository = inspecaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemRepository = itemRepository;
        this.objetoItemRepository = objetoItemRepository;
        this.inspecaoItemMapper = inspecaoItemMapper;

    }

    @Transactional
    public InspecaoItemDtoRead create(InspecaoItemDtoCreate inspecaoItemDtoCreate) {

        InspecaoEntity inspecaoEntity = inspecaoRepository.findById(inspecaoItemDtoCreate.getInspecaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Inspeção não encontrada!"));

        if (inspecaoEntity.getStatus() != InspecaoStatusEnum.EM_ANDAMENTO) {
            throw new ConflitoException(
                    "A inspeção não está em andamento!");
        }

        ItemEntity itemEntity = itemRepository.findById(inspecaoItemDtoCreate.getItemId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado!"));


        if (objetoItemRepository.findByObjetoAndItem(inspecaoEntity.getObjeto(), itemEntity).isEmpty()) {
            throw new ConflitoException("Esse item não pertence ao objeto!");
        }

        if (inspecaoItemRepository.findByInspecaoAndItem(inspecaoEntity, itemEntity).isPresent()) {
            throw new ConflitoException("Esse item já foi lançado nesta inspeção!");
        }

        if (inspecaoItemDtoCreate.getStatus() == StatusItemEnum.NOK
        && (inspecaoItemDtoCreate.getObservacao() == null
        || inspecaoItemDtoCreate.getObservacao().isBlank())){
            throw new ConflitoException("A observação é obrigatória quando o item não está OK!");
        }

        UsuarioEntity usuarioEntity = usuarioRepository.findById(1L).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado!"));


        InspecaoItemEntity inspecaoItem = inspecaoItemMapper.inspecaoItemCreateEntity(inspecaoItemDtoCreate);


        inspecaoItem.setInspecao(inspecaoEntity);
        inspecaoItem.setItem(itemEntity);
        inspecaoItem.setCriadoPor(usuarioEntity);
        inspecaoItem.setCriadoEm(LocalDateTime.now());
        inspecaoItem.setResponsavel(usuarioEntity.getResponsavel());

        InspecaoItemEntity salvo = inspecaoItemRepository.save(inspecaoItem);

        return inspecaoItemMapper.inspecaoItemReadEntity(salvo);
    }




}
