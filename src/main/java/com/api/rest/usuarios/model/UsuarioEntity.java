package com.api.rest.usuarios.model;


import jakarta.persistence.*;

@Table (name = "usuarios")
@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

}
