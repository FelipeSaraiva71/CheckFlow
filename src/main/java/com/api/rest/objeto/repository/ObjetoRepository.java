package com.api.rest.objeto.repository;

import com.api.rest.objeto.model.ObjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObjetoRepository extends JpaRepository<ObjetoEntity, Long> {
    Optional<ObjetoEntity> findByIdentificacao(String identificacao);
}
