package com.springlessons.clinicadmin.controller;

import com.springlessons.clinicadmin.dto.SpecializationDtoResponse;
import com.springlessons.clinicadmin.entity.Doctor;
import com.springlessons.clinicadmin.entity.Specialization;
import com.springlessons.clinicadmin.exception.AdminException;
import com.springlessons.clinicadmin.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/specialization")
public class SpecializationController {
    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    /**
     * Возвращает форму добавления специализации
     */
    @GetMapping("/form")
    public String getSpecializationAddForm(Specialization specialization) {
        return "specialization/specialization-add-form";
    }

    /**
     * Обработка запроса на добавления специализации.
     * В случае успешного добавления перенаправляет запрос /specialization/form,
     * в запросе передается идентификатор добавленной специализации
     * В случае неудачи возвращает текущую страницу с информацией об ошибке
     */
    @PostMapping
    public String addSpecialization(@Valid Specialization specialization,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) return "specialization/specialization-add-form";
        try {
            return "redirect:/specialization/form?id=" + specializationService.addSpecialization(specialization);
        } catch (AdminException e) {
            model.addAttribute("error", e.getMessage());
            return "specialization/specialization-add-form";
        }
        /*return ResponseEntity
                .created(URI.create("http://localhost:8080/specialization/" +
                        specializationService.addSpecialization(specialization)))
                .build();*/
    }

    /*@GetMapping("/specializations/inactive")
    public List<SpecializationDtoResponse> getInactiveSpecialization(){
        return specializationService.getInactiveSpecializations();
    }*/
}


