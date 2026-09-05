package com.api.rest.objeto_item.repository;

import com.api.rest.objeto_item.model.ObjetoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetoItemRepository extends JpaRepository<ObjetoItemEntity, Long> {
}
