package com.api.rest.usuarios.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class UsuarioAdmDtoCreate {

    @NotBlank(message = "Nome é obrigatório!")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ -]+$", message = "Use apenas letras!")
    @Size(min = 2, max = 20,message = "Nome deve ter entre 2 e 20 caracteres!")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório!")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ -]+$", message = "Use apenas letras!")
    @Size(min = 2, max = 50, message = "Sobrenome deve ter entre 2 e 50 caracteres!")
    private String sobrenome;

    @NotBlank(message = "Telefone é obrigatório!")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter apenas dígitos e ter entre 10 e 11 números!")
    private String telefone;

    @NotBlank(message = "E-mail é obrigatório!")
    @Email(message = "E-mail inválido!")
    @Size( max = 150, message = "E-mail deve ter no máximo 150 caracteres!")
    private String email;

    @NotBlank(message = "Senha é obrigatório!")
    @Size(min = 4, max = 60)
    private String password;

    @NotNull(message = "Responsável é obrigatório!")
    private Long responsavelId;

}
