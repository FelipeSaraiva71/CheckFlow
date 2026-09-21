package com.api.rest.usuarios.repository;

import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.model.UsuarioTipoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByEmail(String email);
    Page<UsuarioEntity> findByTipo(UsuarioTipoEnum tipo, Pageable pageable);
}
