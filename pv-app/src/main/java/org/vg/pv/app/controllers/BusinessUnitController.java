package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.BusinessUnit;
import org.vg.pv.app.services.BusinessUnitService;

import java.util.List;

@RestController
@RequestMapping("/api/business-units")
public class BusinessUnitController {

    private final BusinessUnitService businessUnitService;

    @Autowired
    public BusinessUnitController(BusinessUnitService businessUnitService) {
        this.businessUnitService = businessUnitService;
    }

    @PostMapping
    public ResponseEntity<BusinessUnit> createBusinessUnit(@RequestBody BusinessUnit businessUnit) {
        BusinessUnit savedUnit = businessUnitService.save(businessUnit);
        return ResponseEntity.ok(savedUnit);
    }

    @GetMapping
    public ResponseEntity<List<BusinessUnit>> getAllBusinessUnits() {
        List<BusinessUnit> units = businessUnitService.findAll();
        return ResponseEntity.ok(units);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessUnit> getBusinessUnitById(@PathVariable Long id) {
        return businessUnitService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessUnit> updateBusinessUnit(@PathVariable Long id, @RequestBody BusinessUnit businessUnit) {
        BusinessUnit updatedUnit = businessUnitService.update(id, businessUnit);
        if (updatedUnit != null) {
            return ResponseEntity.ok(updatedUnit);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessUnit(@PathVariable Long id) {
        businessUnitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
