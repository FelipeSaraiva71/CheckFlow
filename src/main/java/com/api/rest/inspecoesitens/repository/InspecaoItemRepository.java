package com.api.rest.inspecoesitens.repository;

import com.api.rest.inspecoesitens.model.InspecaoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspecaoItemRepository extends JpaRepository<InspecaoItemEntity, Long> {
}
