package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.LocationType;
import org.vg.pv.app.repositories.LocationTypeRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationTypeService {

    private final LocationTypeRepository locationTypeRepository;

    @Autowired
    public LocationTypeService(LocationTypeRepository locationTypeRepository) {
        this.locationTypeRepository = locationTypeRepository;
    }

    public LocationType save(LocationType locationType) {
        locationType.setCreatedAt(OffsetDateTime.now());
        locationType.setUpdatedAt(OffsetDateTime.now());
        return locationTypeRepository.save(locationType);
    }

    public List<LocationType> findAll() {
        return locationTypeRepository.findAll();
    }

    public Optional<LocationType> findById(Long id) {
        return locationTypeRepository.findById(id);
    }

    public LocationType update(Long id, LocationType locationTypeDetails) {
        return locationTypeRepository.findById(id).map(locationType -> {
            locationType.setName(locationTypeDetails.getName());
            locationType.setCode(locationTypeDetails.getCode());
            locationType.setUpdatedAt(OffsetDateTime.now());
            return locationTypeRepository.save(locationType);
        }).orElse(null);
    }

    public void delete(Long id) {
        locationTypeRepository.deleteById(id);
    }
}
