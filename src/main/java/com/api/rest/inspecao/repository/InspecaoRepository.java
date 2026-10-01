package com.api.rest.inspecao.repository;

import com.api.rest.inspecao.model.InspecaoEntity;
import com.api.rest.objeto.model.ObjetoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspecaoRepository extends JpaRepository<InspecaoEntity, Long> {
    Page<InspecaoEntity> findByCriadoPorId(Long id, Pageable pageable
    );
}
