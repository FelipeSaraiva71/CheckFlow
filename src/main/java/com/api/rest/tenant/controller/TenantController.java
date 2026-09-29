package com.api.rest.tenant.controller;


import com.api.rest.tenant.dto.TenantDtoCreate;
import com.api.rest.tenant.dto.TenantDtoRead;
import com.api.rest.tenant.dto.TenantDtoUpdate;
import com.api.rest.tenant.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenants")
public class TenantController {


    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {

        this.tenantService = tenantService;

    }

    @PostMapping
    public ResponseEntity<TenantDtoRead> saveTenant(@RequestBody TenantDtoCreate tenantDtoCreate) {
        TenantDtoRead tenantsalvo = tenantService.saveTenant(tenantDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantsalvo);

    }

    @GetMapping
    public ResponseEntity<Page<TenantDtoRead>> findAllTenant(@PageableDefault(size = 10, sort = "responsavel") Pageable pageable) {
        return ResponseEntity.ok(tenantService.findByAll(pageable));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantDtoRead> update(@PathVariable Long id, @Valid @RequestBody TenantDtoUpdate tenantDtoUpdate) {
        TenantDtoRead tenantAtualizado = tenantService.update(id, tenantDtoUpdate);
        return ResponseEntity.ok(tenantAtualizado);

    }

}
