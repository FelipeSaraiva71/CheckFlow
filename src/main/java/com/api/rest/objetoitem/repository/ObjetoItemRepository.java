package com.api.rest.objetoitem.repository;

import com.api.rest.objetoitem.model.ObjetoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetoItemRepository extends JpaRepository<ObjetoItemEntity, Long> {
}
