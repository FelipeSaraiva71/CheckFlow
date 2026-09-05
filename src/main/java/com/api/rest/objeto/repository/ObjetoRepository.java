package com.api.rest.objeto.repository;

import com.api.rest.objeto.model.ObjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetoRepository extends JpaRepository<ObjetoEntity, Long> {
}
