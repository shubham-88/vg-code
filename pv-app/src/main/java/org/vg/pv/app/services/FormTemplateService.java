package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vg.pv.app.entities.FormTemplate;
import org.vg.pv.app.repositories.FormTemplateRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FormTemplateService {

    private final FormTemplateRepository formTemplateRepository;

    @Autowired
    public FormTemplateService(FormTemplateRepository formTemplateRepository) {
        this.formTemplateRepository = formTemplateRepository;
    }

    public FormTemplate save(FormTemplate formTemplate) {
        formTemplate.setCreatedAt(OffsetDateTime.now());
        formTemplate.setUpdatedAt(OffsetDateTime.now());
        return formTemplateRepository.save(formTemplate);
    }

    public FormTemplate update(Long id, FormTemplate formTemplateDetails) {
        Optional<FormTemplate> optionalFormTemplate = formTemplateRepository.findById(id);
        if (optionalFormTemplate.isPresent()) {
            FormTemplate formTemplate = optionalFormTemplate.get();
            formTemplate.setName(formTemplateDetails.getName());
            formTemplate.setLocation(formTemplateDetails.getLocation());
            formTemplate.setAttributes(formTemplateDetails.getAttributes());
            formTemplate.setUpdatedAt(OffsetDateTime.now());
            return formTemplateRepository.save(formTemplate);
        }
        return null; // Or throw an exception
    }

    public void delete(Long id) {
        formTemplateRepository.deleteById(id);
    }

    public List<FormTemplate> findAll() {
        return formTemplateRepository.findAll();
    }

    public Optional<FormTemplate> findById(Long id) {
        return formTemplateRepository.findById(id);
    }
}
