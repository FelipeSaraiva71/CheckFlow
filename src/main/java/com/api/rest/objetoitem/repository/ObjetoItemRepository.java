package com.api.rest.objetoitem.repository;

import com.api.rest.itens.model.ItemEntity;
import com.api.rest.objeto.model.ObjetoEntity;
import com.api.rest.objetoitem.model.ObjetoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObjetoItemRepository extends JpaRepository<ObjetoItemEntity, Long> {


    List<ObjetoItemEntity> findByObjeto(ObjetoEntity objeto);
    Optional<ObjetoItemEntity> findByObjetoAndItem(ObjetoEntity objeto, ItemEntity item);
    long countByObjeto(ObjetoEntity objeto);


}

