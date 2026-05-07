package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.Spool;
import org.vg.pv.app.services.SpoolService;

import java.util.List;

@RestController
@RequestMapping("/api/spools")
public class SpoolController {

    private final SpoolService spoolService;

    @Autowired
    public SpoolController(SpoolService spoolService) {
        this.spoolService = spoolService;
    }

    @PostMapping
    public ResponseEntity<Spool> createSpool(@RequestBody Spool spool) {
        Spool savedSpool = spoolService.save(spool);
        return ResponseEntity.ok(savedSpool);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Spool>> createSpools(@RequestBody List<Spool> spools) {
        List<Spool> savedSpools = spoolService.saveAll(spools);
        return ResponseEntity.ok(savedSpools);
    }

    @GetMapping
    public ResponseEntity<List<Spool>> getAllSpools() {
        List<Spool> spools = spoolService.findAll();
        return ResponseEntity.ok(spools);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spool> getSpoolById(@PathVariable Long id) {
        return spoolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spool> updateSpool(@PathVariable Long id, @RequestBody Spool spool) {
        Spool updatedSpool = spoolService.update(id, spool);
        if (updatedSpool != null) {
            return ResponseEntity.ok(updatedSpool);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpool(@PathVariable Long id) {
        spoolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
