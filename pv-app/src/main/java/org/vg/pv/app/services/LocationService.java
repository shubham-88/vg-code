package org.vg.pv.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vg.pv.app.entities.Location;
import org.vg.pv.app.repositories.LocationRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location save(Location location) {
        location.setCreatedAt(OffsetDateTime.now());
        location.setUpdatedAt(OffsetDateTime.now());
        return locationRepository.save(location);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    public Location update(Long id, Location locationDetails) {
        return locationRepository.findById(id).map(location -> {
            location.setName(locationDetails.getName());
            location.setLocationType(locationDetails.getLocationType());
            location.setParentLocation(locationDetails.getParentLocation());
            location.setBusinessUnit(locationDetails.getBusinessUnit());
            location.setUpdatedAt(OffsetDateTime.now());
            return locationRepository.save(location);
        }).orElse(null);
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
