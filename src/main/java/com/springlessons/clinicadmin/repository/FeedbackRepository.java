package com.springlessons.clinicadmin.repository;

import com.springlessons.clinicadmin.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
