package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.Supplier;
import org.vg.pv.app.repositories.SupplierRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier save(Supplier supplier) {
        supplier.setCreatedAt(OffsetDateTime.now());
        supplier.setUpdatedAt(OffsetDateTime.now());
        return supplierRepository.save(supplier);
    }

    public List<Supplier> saveAll(List<Supplier> suppliers) {
        OffsetDateTime now = OffsetDateTime.now();
        suppliers.forEach(supplier -> {
            supplier.setCreatedAt(now);
            supplier.setUpdatedAt(now);
        });
        return supplierRepository.saveAll(suppliers);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier update(Long id, Supplier supplierDetails) {
        return supplierRepository.findById(id).map(supplier -> {
            supplier.setName(supplierDetails.getName());
            supplier.setCode(supplierDetails.getCode());
            supplier.setIsActive(supplierDetails.getIsActive());
            supplier.setSortOrder(supplierDetails.getSortOrder());
            supplier.setUpdatedAt(OffsetDateTime.now());
            return supplierRepository.save(supplier);
        }).orElse(null);
    }

    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}
