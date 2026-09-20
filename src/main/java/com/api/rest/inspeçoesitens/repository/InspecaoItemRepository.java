package com.api.rest.inspeçoesitens.repository;

import com.api.rest.inspeçoesitens.model.InspecaoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspecaoItemRepository extends JpaRepository<InspecaoItemEntity, Long> {
}
