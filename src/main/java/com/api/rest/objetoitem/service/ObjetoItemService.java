package com.api.rest.objetoitem.service;


import com.api.rest.exception.ConflitoException;
import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.itens.dto.ItemDtoCreate;
import com.api.rest.itens.dto.ItemDtoRead;
import com.api.rest.itens.dto.ItemDtoUpdate;
import com.api.rest.itens.mapper.ItemMapper;
import com.api.rest.itens.model.ItemEntity;
import com.api.rest.itens.repository.ItemRepository;
import com.api.rest.objeto.mapper.ObjetoMapper;
import com.api.rest.objeto.model.ObjetoEntity;
import com.api.rest.objeto.repository.ObjetoRepository;
import com.api.rest.objetoitem.dto.ObjetoItemDtoCreate;
import com.api.rest.objetoitem.dto.ObjetoItemDtoRead;
import com.api.rest.objetoitem.dto.ObjetoItemDtoUpdate;
import com.api.rest.objetoitem.mapper.ObjetoItemMapper;
import com.api.rest.objetoitem.model.ObjetoItemEntity;
import com.api.rest.objetoitem.repository.ObjetoItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjetoItemService {


    private final ObjetoItemRepository objetoItemRepository;
    private final ObjetoRepository objetoRepository;
    private final ItemRepository itemRepository;
    private final ObjetoItemMapper objetoItemMapper;
    private final ObjetoMapper objetoMapper;
    private final ItemMapper itemMapper;


    public ObjetoItemService(ObjetoItemRepository objetoItemRepository, ObjetoRepository objetoRepository, ItemRepository itemRepository, ObjetoItemMapper objetoItemMapper, ObjetoMapper objetoMapper, ItemMapper itemMapper) {

        this.objetoItemRepository = objetoItemRepository;
        this.objetoRepository = objetoRepository;
        this.itemRepository = itemRepository;
        this.objetoItemMapper = objetoItemMapper;
        this.objetoMapper = objetoMapper;
        this.itemMapper = itemMapper;

    }

    @Transactional
    public ObjetoItemDtoRead create(ObjetoItemDtoCreate dto) {

        // Verifica se o Objeto já existe
        if (objetoRepository.findByIdentificacao(
                dto.getObjeto().getIdentificacao()).isPresent()) {

            throw new ConflitoException(
                    "Já existe um objeto com essa identificação"
            );
        }

        // Cria o Objeto
        ObjetoEntity objeto = new ObjetoEntity();

        objeto.setNome(dto.getObjeto().getNome());
        objeto.setIdentificacao(dto.getObjeto().getIdentificacao());
        objeto.setDescricao(dto.getObjeto().getDescricao());

        ObjetoEntity objetoSalvo = objetoRepository.save(objeto);

        List<ItemEntity> itensSalvo = new ArrayList<>();


        // Processa os Itens
        for (ItemDtoCreate itemDto : dto.getItem()) {

            ItemEntity item;

            // Verifica se o Item já existe
            Optional<ItemEntity> itemExistente =
                    itemRepository.findByNome(itemDto.getNome());

            if (itemExistente.isPresent()) {

                // Reutiliza o Item existente
                item = itemExistente.get();

            } else {

                // Cria um novo Item
                item = new ItemEntity();
                item.setNome(itemDto.getNome());

                item = itemRepository.save(item);
            }

            // Verifica se a relação já existe
            Optional<ObjetoItemEntity> relacaoExistente =
                    objetoItemRepository.findByObjetoAndItem(objetoSalvo, item);

            if (relacaoExistente.isEmpty()) {

                ObjetoItemEntity relacao = new ObjetoItemEntity();

                relacao.setObjeto(objetoSalvo);
                relacao.setItem(item);

                objetoItemRepository.save(relacao);
            }

            itensSalvo.add(item);
        }


        return objetoItemMapper.objetoItemReadEntity(
                objetoMapper.readObjetoDto(objetoSalvo),
                itensSalvo.stream()
                        .map(itemMapper::itemDtoRead)
                        .toList()
        );
    }


    public Page<ObjetoItemDtoRead> findAll(Pageable pageable) {

        return objetoRepository.findAll(pageable).map(objeto -> {

            List<ObjetoItemEntity> relacoes = objetoItemRepository.findByObjeto(objeto);

            List<ItemDtoRead> itens = relacoes.stream().map(relacao -> itemMapper.itemDtoRead(relacao.getItem()))
                    .toList();

            return objetoItemMapper.objetoItemReadEntity(objetoMapper.readObjetoDto(objeto), itens);
        });
    }
    @Transactional
    public ObjetoItemDtoRead update(ObjetoItemDtoUpdate dto) {

        // Verifica se o Objeto existe
        ObjetoEntity objeto = objetoRepository.findById(dto.getObjetoId())
                .orElseThrow(()-> new RecursoNaoEncontradoException("Objeto não encontrado!"));

        Optional<ObjetoEntity> objetoExistente =
                objetoRepository.findByIdentificacao(
                        dto.getObjeto().getIdentificacao()
                );

        if (objetoExistente.isPresent()
                && !objetoExistente.get().getId().equals(dto.getObjetoId())) {

            throw new ConflitoException(
                    "Já existe um objeto com essa identificação!");
        }

        // Atualiza o Objeto
        objeto.setNome(dto.getObjeto().getNome());
        objeto.setIdentificacao(dto.getObjeto().getIdentificacao());
        objeto.setDescricao(dto.getObjeto().getDescricao());

        ObjetoEntity objetoSalvo = objetoRepository.save(objeto);


        // Processa os Itens
        for (ItemDtoUpdate itemDto : dto.getItem()) {

            ItemEntity item;

            // Procura o Item pelo nome
            Optional<ItemEntity> itemExistente =
                    itemRepository.findByNome(itemDto.getNome());

            if (itemExistente.isPresent()) {

                // Item já existe
                item = itemExistente.get();

            } else {

                // Item não existe → cria
                item = new ItemEntity();
                item.setNome(itemDto.getNome());

                item = itemRepository.save(item);
            }


            // Verifica se a relação já existe
            Optional<ObjetoItemEntity> relacaoExistente =
                    objetoItemRepository.findByObjetoAndItem(objetoSalvo, item);

            if (relacaoExistente.isEmpty()) {

                // Cria somente a relação
                ObjetoItemEntity relacao = new ObjetoItemEntity();

                relacao.setObjeto(objetoSalvo);
                relacao.setItem(item);

                objetoItemRepository.save(relacao);
            }
        }


        // Busca novamente todas as relações atuais
        List<ObjetoItemEntity> relacoes =
                objetoItemRepository.findByObjeto(objetoSalvo);

        List<ItemDtoRead> itens = relacoes.stream()
                .map(relacao -> itemMapper.itemDtoRead(relacao.getItem()))
                .toList();


        // Retorna o estado atual completo
        return objetoItemMapper.objetoItemReadEntity(
                objetoMapper.readObjetoDto(objetoSalvo),
                itens
        );
    }
    public void delete(Long objetoId, Long itemId){


        ObjetoEntity objeto = objetoRepository.findById(objetoId).orElseThrow(()-> new RecursoNaoEncontradoException("Objeto não encontrado!"));

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(()-> new RecursoNaoEncontradoException("Item não encontrado!"));

        Optional<ObjetoItemEntity> relacao =
                objetoItemRepository.findByObjetoAndItem(objeto, item);

        if (relacao.isEmpty()) {
            throw new RecursoNaoEncontradoException(
                    "Relação entre objeto e item não encontrada!"
            );
        }

         objetoItemRepository.delete(relacao.get());

    }

}
