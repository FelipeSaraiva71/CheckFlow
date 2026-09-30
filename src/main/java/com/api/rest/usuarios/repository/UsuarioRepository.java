package com.api.rest.usuarios.repository;

import com.api.rest.objeto.model.ObjetoEntity;
import com.api.rest.objetoitem.model.ObjetoItemEntity;
import com.api.rest.usuarios.model.UsuarioEntity;
import com.api.rest.usuarios.model.UsuarioTipoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByEmail(String email);
    @EntityGraph(attributePaths = "responsavel")
    Page<UsuarioEntity> findByTipo(UsuarioTipoEnum tipo, Pageable pageable);
    boolean existsByEmailAndIdNot(String email, Long id);
}
