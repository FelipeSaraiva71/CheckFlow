package com.api.rest.inspecoesitens.controller;

import com.api.rest.inspecoesitens.dto.InspecaoComItensDtoRead;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoCreate;
import com.api.rest.inspecoesitens.dto.InspecaoItemDtoRead;
import com.api.rest.inspecoesitens.service.InspecaoItensService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inspecao/itens")
public class InspecaoItemController {

    private final InspecaoItensService inspecaoItensService;


    public InspecaoItemController(InspecaoItensService inspecaoItensService) {

        this.inspecaoItensService = inspecaoItensService;
    }


    @PostMapping
    public ResponseEntity<InspecaoItemDtoRead> create(@Valid @RequestBody InspecaoItemDtoCreate inspecaoItemDtoCreate) {
        InspecaoItemDtoRead inspecaoItemDtoRead = inspecaoItensService.create(inspecaoItemDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(inspecaoItemDtoRead);
    }

    @GetMapping("/inspecao/{id}")
    public ResponseEntity<InspecaoComItensDtoRead> listByInspecao(@PathVariable Long id) {
        return ResponseEntity.ok(inspecaoItensService.listByInspecao(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Page<InspecaoComItensDtoRead>> listAllUsuario(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(inspecaoItensService.listAllUsuario(id, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<InspecaoComItensDtoRead>> listAll(Pageable pageable) {
        return ResponseEntity.ok(inspecaoItensService.listAll(pageable));
    }
}