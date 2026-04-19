package org.vg.pv.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vg.pv.app.entities.FormFieldType;

@Repository
public interface FormFieldTypeRepository extends JpaRepository<FormFieldType, Long> {
}
