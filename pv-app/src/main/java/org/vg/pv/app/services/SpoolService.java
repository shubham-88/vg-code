package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.Spool;
import org.vg.pv.app.repositories.SpoolRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpoolService {

    private final SpoolRepository spoolRepository;

    @Autowired
    public SpoolService(SpoolRepository spoolRepository) {
        this.spoolRepository = spoolRepository;
    }

    public Spool save(Spool spool) {
        spool.setCreatedAt(OffsetDateTime.now());
        spool.setUpdatedAt(OffsetDateTime.now());
        return spoolRepository.save(spool);
    }

    public List<Spool> saveAll(List<Spool> spools) {
        OffsetDateTime now = OffsetDateTime.now();
        spools.forEach(spool -> {
            spool.setCreatedAt(now);
            spool.setUpdatedAt(now);
        });
        return spoolRepository.saveAll(spools);
    }

    public List<Spool> findAll() {
        return spoolRepository.findAll();
    }

    public Optional<Spool> findById(Long id) {
        return spoolRepository.findById(id);
    }

    public Spool update(Long id, Spool spoolDetails) {
        return spoolRepository.findById(id).map(spool -> {
            spool.setName(spoolDetails.getName());
            spool.setCode(spoolDetails.getCode());
            spool.setSpoolNumber(spoolDetails.getSpoolNumber());
            spool.setSelfWeight(spoolDetails.getSelfWeight());
            spool.setIsActive(spoolDetails.getIsActive());
            spool.setSortOrder(spoolDetails.getSortOrder());
            spool.setUpdatedAt(OffsetDateTime.now());
            return spoolRepository.save(spool);
        }).orElse(null);
    }

    public void delete(Long id) {
        spoolRepository.deleteById(id);
    }
}
