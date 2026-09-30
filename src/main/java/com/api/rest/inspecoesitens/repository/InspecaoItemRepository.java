package com.api.rest.inspecoesitens.repository;

import com.api.rest.inspecao.model.InspecaoEntity;
import com.api.rest.inspecoesitens.model.InspecaoItemEntity;
import com.api.rest.itens.model.ItemEntity;
import com.api.rest.objeto.model.ObjetoEntity;
import com.api.rest.objetoitem.model.ObjetoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InspecaoItemRepository extends JpaRepository<InspecaoItemEntity, Long> {
    Optional<InspecaoItemEntity> findByInspecaoAndItem(InspecaoEntity inspecao, ItemEntity item);
    long countByInspecao(InspecaoEntity inspecao);

}
