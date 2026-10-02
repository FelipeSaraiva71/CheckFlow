package com.api.rest.usuarios.controller;

import com.api.rest.usuarios.dto.UsuarioDevDtoUpdate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.service.UsuarioDevService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/dev")
public class UsuarioDevController {

    private final UsuarioDevService usuarioDevService;

    public UsuarioDevController(UsuarioDevService usuarioDevService) {

        this.usuarioDevService = usuarioDevService;
    }


    @GetMapping
    public ResponseEntity<Page<UsuarioDtoRead>> findAllAdm(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(usuarioDevService.findByAllDev(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoRead> update(@PathVariable Long id, @Valid @RequestBody UsuarioDevDtoUpdate usuarioDevDtoUpdate) {
        UsuarioDtoRead usuarioAtualizado = usuarioDevService.updateDev(id, usuarioDevDtoUpdate);
        return ResponseEntity.ok(usuarioAtualizado);
    }

}
