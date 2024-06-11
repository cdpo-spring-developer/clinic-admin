package com.springlessons.clinicadmin.services;

import com.springlessons.clinicadmin.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }
}
