package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.BusinessUnit;
import org.vg.pv.app.repositories.BusinessUnitRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BusinessUnitService {

    private final BusinessUnitRepository businessUnitRepository;

    @Autowired
    public BusinessUnitService(BusinessUnitRepository businessUnitRepository) {
        this.businessUnitRepository = businessUnitRepository;
    }

    public BusinessUnit save(BusinessUnit businessUnit) {
        businessUnit.setCreatedAt(OffsetDateTime.now());
        businessUnit.setUpdatedAt(OffsetDateTime.now());
        return businessUnitRepository.save(businessUnit);
    }

    public List<BusinessUnit> findAll() {
        return businessUnitRepository.findAll();
    }

    public Optional<BusinessUnit> findById(Long id) {
        return businessUnitRepository.findById(id);
    }

    public BusinessUnit update(Long id, BusinessUnit businessUnitDetails) {
        return businessUnitRepository.findById(id).map(businessUnit -> {
            businessUnit.setCode(businessUnitDetails.getCode());
            businessUnit.setName(businessUnitDetails.getName());
            businessUnit.setUpdatedAt(OffsetDateTime.now());
            return businessUnitRepository.save(businessUnit);
        }).orElse(null);
    }

    public void delete(Long id) {
        businessUnitRepository.deleteById(id);
    }
}
