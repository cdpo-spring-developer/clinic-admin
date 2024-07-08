package com.springlessons.clinicadmin.controller;

import com.springlessons.clinicadmin.entity.Feedback;
import com.springlessons.clinicadmin.entity.Patient;
import com.springlessons.clinicadmin.repository.FeedbackRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientContoller {
   private final FeedbackRepository feedbackRepository;

    public PatientContoller(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    @PreAuthorize("hasRole('ROLE_CONT_MANAGER') and hasRole('ROLE_MODERATOR')")
    @PostMapping("patient/feedback")
    public String addFeedback(@Valid Feedback feedback, @Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "patient/feedback-add-form";
        // some logic
        return null;
    }

    @PreAuthorize("hasRole('ROLE_CONT_MANAGER') and hasRole('ROLE_MODERATOR')")
    @PostMapping("patient/feedback")
    public String setActiveFeedback(@Valid Feedback feedback, @Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "patient/feedback-add-form";
        // some logic
        return null;
    }
}
