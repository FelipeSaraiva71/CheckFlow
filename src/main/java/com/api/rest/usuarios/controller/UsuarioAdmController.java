package com.api.rest.usuarios.controller;


import com.api.rest.usuarios.dto.UsuarioAdmDtoCreate;
import com.api.rest.usuarios.dto.UsuarioAdmDtoUpdate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.service.UsuarioAdmService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/adm")
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

    @GetMapping
    public ResponseEntity<Page<UsuarioDtoRead>> findAllAdm(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(usuarioAdmService.findByAllAdm(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoRead> update(@PathVariable Long id, @Valid @RequestBody UsuarioAdmDtoUpdate usuarioAdmDtoUpdate) {
        UsuarioDtoRead usuarioAtualizado = usuarioAdmService.updateAdm(id, usuarioAdmDtoUpdate);
        return ResponseEntity.ok(usuarioAtualizado);
    }


}
