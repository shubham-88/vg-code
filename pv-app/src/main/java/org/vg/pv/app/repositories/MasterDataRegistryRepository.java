package org.vg.pv.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vg.pv.app.entities.MasterDataRegistry;

@Repository
public interface MasterDataRegistryRepository extends JpaRepository<MasterDataRegistry, Long> {
}
