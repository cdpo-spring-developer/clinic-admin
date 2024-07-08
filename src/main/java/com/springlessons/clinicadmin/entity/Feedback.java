package com.springlessons.clinicadmin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime createdAt;

    @NotNull
    @NotEmpty
    private String text;

    @Column(name = "is_active",
            columnDefinition = "BOOLEAN DEFAULT true",
            insertable = false)
    boolean isActive;
}
