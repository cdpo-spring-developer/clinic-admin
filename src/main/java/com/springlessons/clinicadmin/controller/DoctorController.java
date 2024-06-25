package com.springlessons.clinicadmin.controller;

import com.springlessons.clinicadmin.entity.Doctor;
import com.springlessons.clinicadmin.entity.Specialization;
import com.springlessons.clinicadmin.service.DoctorService;
import com.springlessons.clinicadmin.service.SpecializationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class DoctorController {
    private final DoctorService doctorService;
    private final SpecializationService specializationService;

    public DoctorController(DoctorService doctorService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
    }


    @GetMapping("/doctors")
    public String getDoctors(Model model){
        List<Doctor> doctors = doctorService.getDoctors();
        model.addAttribute("doctor_list", doctors);
        List<Specialization> specializations = specializationService
                .getSpecialization();
        model.addAttribute("specialization_list", specializations);
        return "doctor/doctors";
    }
    @GetMapping("/doctor/{id}")
    public String getDoctorById(@PathVariable int id, Model model){
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor_info", doctor);
        // doctor_info - ссылка на doctor из html
        return "doctor/doctor";
    }
    // метод вернет форму
    @GetMapping("/doctor/form")
    public String getDoctorAddForm(){
        return "doctor/doctor-add-form";
    }
    // обработка данных из формы
    @PostMapping("/doctor")
    public String addDoctor(Doctor doctor){
        // добавление информации о докторе
        return "redirect:/doctor/form";
    }


}
