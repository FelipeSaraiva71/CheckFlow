package com.api.rest.inspecionavel_itens.model;

import com.api.rest.inspecionavel.model.InspecionavelEntity;
import com.api.rest.itens.model.ItemEntity;
import jakarta.persistence.*;

@Table(name = "itens_inspecionaveis")
public class InspecionavelItensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecionavel_id")
    private InspecionavelEntity inspecionavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;
}
