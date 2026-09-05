package com.api.rest.inspeçoes_itens.repository;

import com.api.rest.inspeçoes_itens.model.InspecaoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspecaoItemRepository extends JpaRepository<InspecaoItemEntity, Long> {
}
