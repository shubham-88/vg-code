package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.FormFieldType;
import org.vg.pv.app.services.FormFieldTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/form-field-types")
public class FormFieldTypeController {

    private final FormFieldTypeService formFieldTypeService;

    @Autowired
    public FormFieldTypeController(FormFieldTypeService formFieldTypeService) {
        this.formFieldTypeService = formFieldTypeService;
    }

    @PostMapping
    public ResponseEntity<FormFieldType> createFormFieldType(@RequestBody FormFieldType formFieldType) {
        FormFieldType savedType = formFieldTypeService.save(formFieldType);
        return ResponseEntity.ok(savedType);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<FormFieldType>> createFormFieldTypes(@RequestBody List<FormFieldType> formFieldTypes) {
        List<FormFieldType> savedTypes = formFieldTypeService.saveAll(formFieldTypes);
        return ResponseEntity.ok(savedTypes);
    }

    @GetMapping
    public ResponseEntity<List<FormFieldType>> getAllFormFieldTypes() {
        List<FormFieldType> types = formFieldTypeService.findAll();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormFieldType> getFormFieldTypeById(@PathVariable Long id) {
        return formFieldTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormFieldType> updateFormFieldType(@PathVariable Long id, @RequestBody FormFieldType formFieldType) {
        FormFieldType updatedType = formFieldTypeService.update(id, formFieldType);
        if (updatedType != null) {
            return ResponseEntity.ok(updatedType);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormFieldType(@PathVariable Long id) {
        formFieldTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
