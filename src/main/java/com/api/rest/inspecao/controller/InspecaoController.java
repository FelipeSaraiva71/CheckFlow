package com.api.rest.inspecao.controller;

import com.api.rest.inspecao.dto.InspecaoDtoCreate;
import com.api.rest.inspecao.dto.InspecaoDtoRead;
import com.api.rest.inspecao.service.InspecaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inspecao")
public class InspecaoController {

    private final InspecaoService inspecaoService;

    public InspecaoController (InspecaoService inspecaoService){

        this.inspecaoService = inspecaoService;
    }

    @PostMapping
    public ResponseEntity<InspecaoDtoRead> create(@Valid @RequestBody InspecaoDtoCreate inspecaoDtoCreate){
        InspecaoDtoRead inspecaoDtoRead = inspecaoService.create(inspecaoDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(inspecaoDtoRead);
    }

    @GetMapping
    public ResponseEntity<Page<InspecaoDtoRead>> listAll(@PageableDefault(size = 5 , sort = "criadoEm")Pageable pageable){
        return ResponseEntity.ok(inspecaoService.listAll(pageable));
    }

    @PatchMapping("/{id}/finalizado")
    public ResponseEntity<InspecaoDtoRead> patchExecutado(@PathVariable Long id){
        InspecaoDtoRead inspecaoDtoRead = inspecaoService.patchExecutado(id);
        return ResponseEntity.ok(inspecaoDtoRead);
    }

    @PatchMapping("/{id}/pendente")
    public ResponseEntity<InspecaoDtoRead> patchPendente(@PathVariable Long id){
        InspecaoDtoRead inspecaoDtoRead = inspecaoService.patchPendente(id);
        return ResponseEntity.ok(inspecaoDtoRead);
    }

    @PatchMapping("/{id}/andamento")
    public ResponseEntity<InspecaoDtoRead> patchAndamento(@PathVariable Long id){
        InspecaoDtoRead inspecaoDtoRead = inspecaoService.patchAndamento(id);
        return ResponseEntity.ok(inspecaoDtoRead);
    }
}
