package com.api.rest.usuarios.controller;


import com.api.rest.usuarios.dto.UsuarioAdmDtoCreate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.service.UsuarioAdmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "usuario/adm")
public class UsuarioAdmController {

    private final UsuarioAdmService usuarioAdmService;

    public UsuarioAdmController(UsuarioAdmService usuarioAdmService) {

        this.usuarioAdmService = usuarioAdmService;
    }


    @PostMapping
    public ResponseEntity<UsuarioDtoRead> saveAdm(@Valid @RequestBody UsuarioAdmDtoCreate usuarioAdmDtoCreate) {
        UsuarioDtoRead usuarioSalvo = usuarioAdmService.saveAdm(usuarioAdmDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }


}
