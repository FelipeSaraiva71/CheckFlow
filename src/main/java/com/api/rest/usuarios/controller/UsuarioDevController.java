package com.api.rest.usuarios.controller;

import com.api.rest.usuarios.dto.UsuarioAdmDtoCreate;
import com.api.rest.usuarios.dto.UsuarioAdmDtoUpdate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.service.UsuarioDevService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/dev")
public class UsuarioDevController {

    private final UsuarioDevService usuarioDevService;

    public UsuarioDevController(UsuarioDevService usuarioDevService) {

        this.usuarioDevService = usuarioDevService;
    }


    @PostMapping
    public ResponseEntity<UsuarioDtoRead> saveAdm(@Valid @RequestBody UsuarioAdmDtoCreate usuarioAdmDtoCreate) {
        UsuarioDtoRead usuarioSalvo = usuarioDevService.saveDev(usuarioAdmDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDtoRead>> findAllAdm(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(usuarioDevService.findByAllDev(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoRead> update(@PathVariable Long id, @Valid @RequestBody UsuarioAdmDtoUpdate usuarioAdmDtoUpdate) {
        UsuarioDtoRead usuarioAtualizado = usuarioDevService.updateDev(id, usuarioAdmDtoUpdate);
        return ResponseEntity.ok(usuarioAtualizado);
    }

}
