package com.api.rest.objetoitem.controller;

import com.api.rest.objetoitem.dto.ObjetoItemDtoCreate;
import com.api.rest.objetoitem.dto.ObjetoItemDtoRead;
import com.api.rest.objetoitem.dto.ObjetoItemDtoUpdate;
import com.api.rest.objetoitem.service.ObjetoItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objeto/itens")
public class ObjetoItemController {

    private final ObjetoItemService objetoItemService;

    public ObjetoItemController(ObjetoItemService objetoItemService) {

        this.objetoItemService = objetoItemService;
    }


    @PostMapping
    public ResponseEntity<ObjetoItemDtoRead> createObjetoItem(@Valid @RequestBody ObjetoItemDtoCreate objetoItemDtoCreate) {
        ObjetoItemDtoRead objetoItemSalvo = objetoItemService.create(objetoItemDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(objetoItemSalvo);

    }

    @GetMapping
    public ResponseEntity<Page<ObjetoItemDtoRead>> findAll(@PageableDefault(size = 1) Pageable pageable) {
        return ResponseEntity.ok(objetoItemService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetoItemDtoRead> update(@Valid @RequestBody ObjetoItemDtoUpdate objetoItemDtoUpdate) {
        ObjetoItemDtoRead objetoItemAtualizado = objetoItemService.update(objetoItemDtoUpdate);
        return ResponseEntity.ok(objetoItemAtualizado);
    }


    @DeleteMapping("/{objetoId}/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable Long objetoId, @PathVariable Long itemId) {
        objetoItemService.delete(objetoId, itemId);
        return ResponseEntity.noContent().build();
    }

}
