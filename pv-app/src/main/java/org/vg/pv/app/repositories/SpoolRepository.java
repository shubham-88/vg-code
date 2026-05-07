package org.vg.pv.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vg.pv.app.entities.Spool;

@Repository
public interface SpoolRepository extends JpaRepository<Spool, Long> {
}
