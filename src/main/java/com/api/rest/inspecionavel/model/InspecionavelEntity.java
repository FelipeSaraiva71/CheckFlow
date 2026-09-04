package com.api.rest.inspecionavel.model;


import com.api.rest.itens.model.ItemEntity;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "inspecionaveis")
@Entity
public class InspecionavelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String identificacao;
    private String descricao;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private List<ItemEntity> item;

}
