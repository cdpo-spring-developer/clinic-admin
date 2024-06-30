package com.springlessons.clinicadmin.repository;

import com.springlessons.clinicadmin.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {


    @Query(nativeQuery = true, value = "SELECT doctor.* " +
            "FROM doctor " +
            "JOIN doctor_specialization ON doctor.id = doctor_specialization.doctor_id " +
            "JOIN specialization ON specialization.id = doctor_specialization.specialization_id " +
            "WHERE code_lat = :code")
    List<Doctor> findDoctorsBySpecializationCode(String code);

}

