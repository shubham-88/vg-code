package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.FormFieldDataType;
import org.vg.pv.app.services.FormFieldDataTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/form-field-data-types")
public class FormFieldDataTypeController {

    private final FormFieldDataTypeService formFieldDataTypeService;

    @Autowired
    public FormFieldDataTypeController(FormFieldDataTypeService formFieldDataTypeService) {
        this.formFieldDataTypeService = formFieldDataTypeService;
    }

    @PostMapping
    public ResponseEntity<FormFieldDataType> createFormFieldDataType(@RequestBody FormFieldDataType formFieldDataType) {
        FormFieldDataType savedType = formFieldDataTypeService.save(formFieldDataType);
        return ResponseEntity.ok(savedType);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<FormFieldDataType>> createFormFieldDataTypes(@RequestBody List<FormFieldDataType> formFieldDataTypes) {
        List<FormFieldDataType> savedTypes = formFieldDataTypeService.saveAll(formFieldDataTypes);
        return ResponseEntity.ok(savedTypes);
    }

    @GetMapping
    public ResponseEntity<List<FormFieldDataType>> getAllFormFieldDataTypes() {
        List<FormFieldDataType> types = formFieldDataTypeService.findAll();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormFieldDataType> getFormFieldDataTypeById(@PathVariable Long id) {
        return formFieldDataTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormFieldDataType> updateFormFieldDataType(@PathVariable Long id, @RequestBody FormFieldDataType formFieldDataType) {
        FormFieldDataType updatedType = formFieldDataTypeService.update(id, formFieldDataType);
        if (updatedType != null) {
            return ResponseEntity.ok(updatedType);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormFieldDataType(@PathVariable Long id) {
        formFieldDataTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
