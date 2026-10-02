package com.api.rest.usuarios.controller;


import com.api.rest.usuarios.dto.UsuarioComumDtoCreate;
import com.api.rest.usuarios.dto.UsuarioComumDtoUpdate;
import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.service.UsuarioComumService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/comum")
public class UsuarioComumController {

    private final UsuarioComumService usuarioComumService;

    public UsuarioComumController(UsuarioComumService usuarioComumService) {

        this.usuarioComumService = usuarioComumService;
    }


    @PostMapping
    public ResponseEntity<UsuarioDtoRead> saveAdm(@Valid @RequestBody UsuarioComumDtoCreate usuarioComumDtoCreate) {
        UsuarioDtoRead usuarioSalvo = usuarioComumService.saveComum(usuarioComumDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDtoRead>> findAllAdm(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(usuarioComumService.findByAllComum(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoRead> update(@PathVariable Long id, @Valid @RequestBody UsuarioComumDtoUpdate usuarioComumDtoUpdate) {
        UsuarioDtoRead usuarioAtualizado = usuarioComumService.updateComum(id, usuarioComumDtoUpdate);
        return ResponseEntity.ok(usuarioAtualizado);
    }

}

