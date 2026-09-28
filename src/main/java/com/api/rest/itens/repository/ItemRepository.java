package com.api.rest.itens.repository;

import com.api.rest.itens.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByNome(String nome);

}
