package com.springlessons.clinicadmin.repository;

import com.springlessons.clinicadmin.entity.Specialization;
import org.springframework.data.repository.CrudRepository;

public interface SpecializationRepository
        extends CrudRepository<Specialization, Integer> {
}
