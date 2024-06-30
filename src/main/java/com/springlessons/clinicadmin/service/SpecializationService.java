package com.springlessons.clinicadmin.service;

import com.springlessons.clinicadmin.dto.SpecializationDtoResponse;
import com.springlessons.clinicadmin.entity.Specialization;
import com.springlessons.clinicadmin.exception.AdminException;
import com.springlessons.clinicadmin.repository.SpecializationRepository;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public int addSpecialization(Specialization specialization) throws AdminException {
        if (specializationRepository.existsByCode(specialization.getCode())){
            throw new AdminException("Специализация с таким кодом уже существует. " + specialization.getCode());
        }
        return specializationRepository.save(specialization).getId();
    }

    public List<SpecializationDtoResponse> getInactiveSpecializations(){
        List<Specialization> inactiveSpecializations =
                specializationRepository.findByIsActiveFalse();
        // 7
        // TODO::
        //  1. получить количество активных и неактивных докторов
        //     неактивных специализаций
        //  2. сформировать List<SpecializationDtoResponse>
        return null;
    }

    public List<Specialization> getSpecializations() {
        Specialization specialization01 = new Specialization();
        specialization01.setId(1);
        specialization01.setName("Невролог");
        specialization01.setCode("neurologist");
        Specialization specialization02 = new Specialization();
        specialization02.setId(2);
        specialization02.setName("ЛОР");
        specialization02.setCode("lor");
        List<Specialization> specializations = specializationRepository.findAll();
        if (specializations.isEmpty()){
            specializations.add(specialization01);
            specializations.add(specialization02);
        }
        return specializations;
    }
}
