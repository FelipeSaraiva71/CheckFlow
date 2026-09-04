package com.api.rest.itens.model;

import com.api.rest.inspecionavel.model.InspecionavelEntity;
import jakarta.persistence.*;

@Table(name = "itens")
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

}
