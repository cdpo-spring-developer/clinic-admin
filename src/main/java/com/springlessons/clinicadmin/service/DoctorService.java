package com.springlessons.clinicadmin.service;

import com.springlessons.clinicadmin.entity.Doctor;
import com.springlessons.clinicadmin.entity.Specialization;
import com.springlessons.clinicadmin.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor getDoctorById(int id){
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setActive(true);
        doctor.setFirstName("Кирилл");
        doctor.setLastName("Потапов");
        doctor.setExperience(15);
        return doctorRepository.findById(id).orElse(doctor);
        // return doctorRepository.findById(id)
        // .orElseThrow(()->new ClinicException("Доктор не найден"));
    }

    public List<Doctor> getDoctors(){
        Doctor doctor01 = new Doctor();
        doctor01.setId(1);
        doctor01.setActive(true);
        doctor01.setFirstName("Кирилл");
        doctor01.setLastName("Потапов");
        doctor01.setExperience(15);

        Doctor doctor02 = new Doctor();
        doctor02.setId(2);
        doctor02.setActive(true);
        doctor02.setFirstName("Ольга");
        doctor02.setLastName("Иванова");
        doctor02.setExperience(22);
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            doctors.add(doctor01);
            doctors.add(doctor02);
        }
        return doctors;
    }

    public List<Doctor> getDoctorsBySpecializationCode(String code) {
        Doctor doctor01 = new Doctor();
        doctor01.setId(1);
        doctor01.setActive(true);
        doctor01.setFirstName("Кирилл");
        doctor01.setLastName("Потапов");
        doctor01.setExperience(15);

        Doctor doctor02 = new Doctor();
        doctor02.setId(2);
        doctor02.setActive(true);
        doctor02.setFirstName("Ольга");
        doctor02.setLastName("Иванова");
        doctor02.setExperience(22);
        List<Doctor> doctors = doctorRepository.findDoctorsBySpecializationCode(code);
        if (doctors.isEmpty()) {
            doctors.add(doctor01);
            doctors.add(doctor02);
        }
        return doctors;
    }

    public int addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor).getId();
    }
}
