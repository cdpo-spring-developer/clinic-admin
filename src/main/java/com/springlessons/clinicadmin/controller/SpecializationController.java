package com.springlessons.clinicadmin.controller;

import com.springlessons.clinicadmin.entity.Specialization;
import com.springlessons.clinicadmin.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
// @RequestMapping("/specialization")
public class SpecializationController {
    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping("/specialization")
    public ResponseEntity<?> addSpecialization(@RequestBody /*@Valid*/ Specialization specialization){
        return ResponseEntity
                .created(URI.create("http://localhost:8080/specialization/" +
                        specializationService.addSpecialization(specialization)))
                .build();
    }
}


