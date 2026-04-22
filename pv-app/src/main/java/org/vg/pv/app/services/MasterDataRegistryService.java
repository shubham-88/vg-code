package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.MasterDataRegistry;
import org.vg.pv.app.repositories.MasterDataRegistryRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MasterDataRegistryService {

    private final MasterDataRegistryRepository masterDataRegistryRepository;

    @Autowired
    public MasterDataRegistryService(MasterDataRegistryRepository masterDataRegistryRepository) {
        this.masterDataRegistryRepository = masterDataRegistryRepository;
    }

    public MasterDataRegistry save(MasterDataRegistry registry) {
        registry.setCreatedAt(OffsetDateTime.now());
        registry.setUpdatedAt(OffsetDateTime.now());
        return masterDataRegistryRepository.save(registry);
    }

    public List<MasterDataRegistry> saveAll(List<MasterDataRegistry> registries) {
        OffsetDateTime now = OffsetDateTime.now();
        registries.forEach(registry -> {
            registry.setCreatedAt(now);
            registry.setUpdatedAt(now);
        });
        return masterDataRegistryRepository.saveAll(registries);
    }

    public List<MasterDataRegistry> findAll() {
        return masterDataRegistryRepository.findAll();
    }

    public Optional<MasterDataRegistry> findById(Long id) {
        return masterDataRegistryRepository.findById(id);
    }

    public MasterDataRegistry update(Long id, MasterDataRegistry registryDetails) {
        return masterDataRegistryRepository.findById(id).map(registry -> {
            registry.setName(registryDetails.getName());
            registry.setTableName(registryDetails.getTableName());
            registry.setUpdatedAt(OffsetDateTime.now());
            return masterDataRegistryRepository.save(registry);
        }).orElse(null);
    }

    public void delete(Long id) {
        masterDataRegistryRepository.deleteById(id);
    }
}
