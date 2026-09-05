package com.api.rest.inspecao.repository;

import com.api.rest.inspecao.model.InspecaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspecaoRepository extends JpaRepository<InspecaoEntity, Long> {
}
