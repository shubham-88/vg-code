package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.FormFieldDataType;
import org.vg.pv.app.repositories.FormFieldDataTypeRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormFieldDataTypeService {

    private final FormFieldDataTypeRepository formFieldDataTypeRepository;

    @Autowired
    public FormFieldDataTypeService(FormFieldDataTypeRepository formFieldDataTypeRepository) {
        this.formFieldDataTypeRepository = formFieldDataTypeRepository;
    }

    public FormFieldDataType save(FormFieldDataType formFieldDataType) {
        formFieldDataType.setCreatedAt(OffsetDateTime.now());
        formFieldDataType.setUpdatedAt(OffsetDateTime.now());
        return formFieldDataTypeRepository.save(formFieldDataType);
    }

    public List<FormFieldDataType> saveAll(List<FormFieldDataType> formFieldDataTypes) {
        OffsetDateTime now = OffsetDateTime.now();
        formFieldDataTypes.forEach(type -> {
            type.setCreatedAt(now);
            type.setUpdatedAt(now);
        });
        return formFieldDataTypeRepository.saveAll(formFieldDataTypes);
    }

    public List<FormFieldDataType> findAll() {
        return formFieldDataTypeRepository.findAll();
    }

    public Optional<FormFieldDataType> findById(Long id) {
        return formFieldDataTypeRepository.findById(id);
    }

    public FormFieldDataType update(Long id, FormFieldDataType formFieldDataTypeDetails) {
        return formFieldDataTypeRepository.findById(id).map(formFieldDataType -> {
            formFieldDataType.setName(formFieldDataTypeDetails.getName());
            formFieldDataType.setShortName(formFieldDataTypeDetails.getShortName());
            formFieldDataType.setDescription(formFieldDataTypeDetails.getDescription());
            formFieldDataType.setUpdatedAt(OffsetDateTime.now());
            return formFieldDataTypeRepository.save(formFieldDataType);
        }).orElse(null);
    }

    public void delete(Long id) {
        formFieldDataTypeRepository.deleteById(id);
    }
}
