package org.vg.pv.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vg.pv.app.entities.FormTemplate;
import org.vg.pv.app.services.FormTemplateService;

import java.util.List;

@RestController
@RequestMapping("/api/forms")
public class PVFormController {

    private final FormTemplateService formTemplateService;

    @Autowired
    public PVFormController(FormTemplateService formTemplateService) {
        this.formTemplateService = formTemplateService;
    }

    @PostMapping
    public ResponseEntity<FormTemplate> createFormTemplate(@RequestBody FormTemplate formTemplate) {
        FormTemplate savedTemplate = formTemplateService.save(formTemplate);
        return ResponseEntity.ok(savedTemplate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormTemplate> updateFormTemplate(@PathVariable Long id, @RequestBody FormTemplate formTemplate) {
        FormTemplate updatedTemplate = formTemplateService.update(id, formTemplate);
        if (updatedTemplate != null) {
            return ResponseEntity.ok(updatedTemplate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormTemplate(@PathVariable Long id) {
        formTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FormTemplate>> getAllFormTemplates() {
        List<FormTemplate> templates = formTemplateService.findAll();
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormTemplate> getFormTemplateById(@PathVariable Long id) {
        return formTemplateService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
