package org.vg.pv.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vg.pv.app.entities.LocationType;

@Repository
public interface LocationTypeRepository extends JpaRepository<LocationType, Long> {
}
