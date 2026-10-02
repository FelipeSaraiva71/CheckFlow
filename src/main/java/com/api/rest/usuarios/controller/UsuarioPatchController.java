package com.api.rest.usuarios.controller;


import com.api.rest.usuarios.dto.UsuarioDtoRead;
import com.api.rest.usuarios.dto.UsuarioPasswordDtoPatch;
import com.api.rest.usuarios.dto.UsuarioStatusDtoPatch;
import com.api.rest.usuarios.service.UsuarioPaatchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/patch")
public class UsuarioPatchController {

    private final UsuarioPaatchService usuarioPaatchService;

    public UsuarioPatchController(UsuarioPaatchService usuarioPaatchService){
        this.usuarioPaatchService = usuarioPaatchService;
    }



    @PatchMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioPasswordDtoPatch usuarioPasswordDtoPatch){
        return usuarioPaatchService.patchPassword(id, usuarioPasswordDtoPatch);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<UsuarioDtoRead> updateStatus(@PathVariable Long id, @Valid @RequestBody UsuarioStatusDtoPatch usuarioStatusDtoPatch){
        return usuarioPaatchService.patchStatus(id, usuarioStatusDtoPatch);
    }
}
