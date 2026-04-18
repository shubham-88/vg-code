package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.LocationType;
import org.vg.pv.app.services.LocationTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/location-types")
public class LocationTypeController {

    private final LocationTypeService locationTypeService;

    @Autowired
    public LocationTypeController(LocationTypeService locationTypeService) {
        this.locationTypeService = locationTypeService;
    }

    @PostMapping
    public ResponseEntity<LocationType> createLocationType(@RequestBody LocationType locationType) {
        LocationType savedType = locationTypeService.save(locationType);
        return ResponseEntity.ok(savedType);
    }

    @GetMapping
    public ResponseEntity<List<LocationType>> getAllLocationTypes() {
        List<LocationType> types = locationTypeService.findAll();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationType> getLocationTypeById(@PathVariable Long id) {
        return locationTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationType> updateLocationType(@PathVariable Long id, @RequestBody LocationType locationType) {
        LocationType updatedType = locationTypeService.update(id, locationType);
        if (updatedType != null) {
            return ResponseEntity.ok(updatedType);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationType(@PathVariable Long id) {
        locationTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
