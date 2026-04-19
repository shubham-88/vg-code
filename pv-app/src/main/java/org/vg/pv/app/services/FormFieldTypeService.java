package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.FormFieldType;
import org.vg.pv.app.repositories.FormFieldTypeRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormFieldTypeService {

    private final FormFieldTypeRepository formFieldTypeRepository;

    @Autowired
    public FormFieldTypeService(FormFieldTypeRepository formFieldTypeRepository) {
        this.formFieldTypeRepository = formFieldTypeRepository;
    }

    public FormFieldType save(FormFieldType formFieldType) {
        formFieldType.setCreatedAt(OffsetDateTime.now());
        formFieldType.setUpdatedAt(OffsetDateTime.now());
        return formFieldTypeRepository.save(formFieldType);
    }

    public List<FormFieldType> saveAll(List<FormFieldType> formFieldTypes) {
        OffsetDateTime now = OffsetDateTime.now();
        formFieldTypes.forEach(type -> {
            type.setCreatedAt(now);
            type.setUpdatedAt(now);
        });
        return formFieldTypeRepository.saveAll(formFieldTypes);
    }

    public List<FormFieldType> findAll() {
        return formFieldTypeRepository.findAll();
    }

    public Optional<FormFieldType> findById(Long id) {
        return formFieldTypeRepository.findById(id);
    }

    public FormFieldType update(Long id, FormFieldType formFieldTypeDetails) {
        return formFieldTypeRepository.findById(id).map(formFieldType -> {
            formFieldType.setName(formFieldTypeDetails.getName());
            formFieldType.setShortName(formFieldTypeDetails.getShortName());
            formFieldType.setDescription(formFieldTypeDetails.getDescription());
            formFieldType.setUpdatedAt(OffsetDateTime.now());
            return formFieldTypeRepository.save(formFieldType);
        }).orElse(null);
    }

    public void delete(Long id) {
        formFieldTypeRepository.deleteById(id);
    }
}
