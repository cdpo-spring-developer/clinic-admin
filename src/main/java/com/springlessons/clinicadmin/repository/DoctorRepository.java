package com.springlessons.clinicadmin.repository;

import com.springlessons.clinicadmin.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
