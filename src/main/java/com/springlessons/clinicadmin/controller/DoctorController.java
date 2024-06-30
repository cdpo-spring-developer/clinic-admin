package com.springlessons.clinicadmin.controller;

import com.springlessons.clinicadmin.entity.Doctor;
import com.springlessons.clinicadmin.entity.Specialization;
import com.springlessons.clinicadmin.service.DoctorService;
import com.springlessons.clinicadmin.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Обработка запросов по управлению врачами
 */
@Controller
public class DoctorController {
    private final DoctorService doctorService;
    private final SpecializationService specializationService;

    public DoctorController(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }

    /**
     * Возвращает форму добавления врача
     */
    @GetMapping("/doctor/form")
    public String getDoctorAddForm(Doctor doctor, Model model) {
        model.addAttribute("specialization_list", specializationService.getSpecializations());
        return "doctor/doctor-add-form";
    }

    /**
     * Обработка запроса на добавления врача.
     * В случае успешного добавления перенаправляет запрос /doctor/form,
     * в запросе передается идентификатор добавленного врача
     * В случае неудачи возвращает текущую страницу с информацией об ошибке
     */
    @PostMapping("/doctor")
    public String addDoctor(@Valid Doctor doctor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "doctor/doctor-add-form";
        return "redirect:/doctor/form?id=" + doctorService.addDoctor(doctor);
    }


    /**
     * Генерирует страницу со списком докторов и специализаций
     */
    @GetMapping("/doctors")
    public String getDoctors(Model model) {
        List<Doctor> doctors = doctorService.getDoctors();
        List<Specialization> specializations = specializationService.getSpecializations();
        model.addAttribute("doctor_list", doctors);
        model.addAttribute("specialization_list", specializations);
        return "doctor/doctors";
    }

    /**
     * Генерирует страницу со списком докторов, отфильтрованнных по коду специализации и
     * списком специализаций
     */
    @GetMapping("/doctor/specialization/{code}")
    public String getDoctorsBySpecializationCode(@PathVariable String code, Model model) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecializationCode(code);
        List<Specialization> specializations = specializationService.getSpecializations();
        model.addAttribute("doctor_list", doctors);
        model.addAttribute("specialization_list", specializations);
        return "doctor/doctors";
    }


    /**
     * Генерирует страницу с детальным описанием врача
     */
    @GetMapping("/doctor/{id}")
    public String getDoctorById(@PathVariable int id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor_info", doctor);
        // doctor_info - ссылка на doctor из html
        return "doctor/doctor";
    }

}
