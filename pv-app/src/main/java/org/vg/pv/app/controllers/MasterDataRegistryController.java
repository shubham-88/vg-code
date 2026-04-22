package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.MasterDataRegistry;
import org.vg.pv.app.services.MasterDataRegistryService;

import java.util.List;

@RestController
@RequestMapping("/api/master-data-registry")
public class MasterDataRegistryController {

    private final MasterDataRegistryService masterDataRegistryService;

    @Autowired
    public MasterDataRegistryController(MasterDataRegistryService masterDataRegistryService) {
        this.masterDataRegistryService = masterDataRegistryService;
    }

    @PostMapping
    public ResponseEntity<MasterDataRegistry> createMasterDataRegistry(@RequestBody MasterDataRegistry registry) {
        MasterDataRegistry savedRegistry = masterDataRegistryService.save(registry);
        return ResponseEntity.ok(savedRegistry);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<MasterDataRegistry>> createMasterDataRegistries(@RequestBody List<MasterDataRegistry> registries) {
        List<MasterDataRegistry> savedRegistries = masterDataRegistryService.saveAll(registries);
        return ResponseEntity.ok(savedRegistries);
    }

    @GetMapping
    public ResponseEntity<List<MasterDataRegistry>> getAllMasterDataRegistries() {
        List<MasterDataRegistry> registries = masterDataRegistryService.findAll();
        return ResponseEntity.ok(registries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterDataRegistry> getMasterDataRegistryById(@PathVariable Long id) {
        return masterDataRegistryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterDataRegistry> updateMasterDataRegistry(@PathVariable Long id, @RequestBody MasterDataRegistry registry) {
        MasterDataRegistry updatedRegistry = masterDataRegistryService.update(id, registry);
        if (updatedRegistry != null) {
            return ResponseEntity.ok(updatedRegistry);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterDataRegistry(@PathVariable Long id) {
        masterDataRegistryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
