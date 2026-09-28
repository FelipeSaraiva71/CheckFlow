package com.api.rest.objetoitem.service;


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

    public ObjetoItemDtoRead create(ObjetoItemDtoCreate objetoItemDtoCreate) {

        ObjetoEntity objetoEntity = new ObjetoEntity();

        objetoEntity.setNome(objetoItemDtoCreate.getObjeto().getNome());
        objetoEntity.setIdentificacao(objetoItemDtoCreate.getObjeto().getIdentificacao());
        objetoEntity.setDescricao(objetoItemDtoCreate.getObjeto().getDescricao());

        ObjetoEntity objetoSalvo = objetoRepository.save(objetoEntity);

        List<ItemEntity> itensSalvo = new ArrayList<>();

        for (ItemDtoCreate itemDto : objetoItemDtoCreate.getItem()) {

            ItemEntity item = new ItemEntity();

            item.setNome(itemDto.getNome());

            ItemEntity itemSalvo = itemRepository.save(item);
            itensSalvo.add(itemSalvo);
            ObjetoItemEntity relacao = new ObjetoItemEntity();


            relacao.setObjeto(objetoSalvo);
            relacao.setItem(itemSalvo);

            objetoItemRepository.save(relacao);
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

        ObjetoEntity objeto = objetoRepository.findById(dto.getObjetoId())
                .orElseThrow();

        // Atualiza o Objeto
        objeto.setNome(dto.getObjeto().getNome());
        objeto.setIdentificacao(dto.getObjeto().getIdentificacao());
        objeto.setDescricao(dto.getObjeto().getDescricao());

        ObjetoEntity objetoSalvo = objetoRepository.save(objeto);

        // Busca as relações atuais do objeto
        List<ObjetoItemEntity> relacoes =
                objetoItemRepository.findByObjeto(objetoSalvo);

        List<ItemEntity> itens = new ArrayList<>();

        for (ItemDtoUpdate itemDto : dto.getItem()) {

            if (itemDto.getId() != null) {

                // Item existente
                ItemEntity item = itemRepository.findById(itemDto.getId())
                        .orElseThrow();

                item.setNome(itemDto.getNome());

                ItemEntity itemSalvo = itemRepository.save(item);

                itens.add(itemSalvo);

            } else {

                // Item novo
                ItemEntity item = new ItemEntity();

                item.setNome(itemDto.getNome());

                ItemEntity itemSalvo = itemRepository.save(item);

                ObjetoItemEntity relacao = new ObjetoItemEntity();
                relacao.setObjeto(objetoSalvo);
                relacao.setItem(itemSalvo);

                objetoItemRepository.save(relacao);

                itens.add(itemSalvo);
            }
        }

        return objetoItemMapper.objetoItemReadEntity(
                objetoMapper.readObjetoDto(objetoSalvo),
                itens.stream()
                        .map(itemMapper::itemDtoRead)
                        .toList()
        );
    }

    public void delete(Long objetoId, Long itemId){

        ObjetoEntity objeto = objetoRepository.findById(objetoId).orElseThrow();

        ItemEntity item = itemRepository.findById(itemId).orElseThrow();

         objetoItemRepository.deleteByObjetoAndItem(objeto, item);

    }

}
