package com.springlessons.clinicadmin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

// POST создание специализации
// В теле
// specialization_name -> name,
// specialization_code -> code,
// description -> description,
// enable -> isActive

// Ответ:
// статус 201
// В заголовках:
// Location: http:localhost:8080/specialization/id



@Data
@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3)
    @JsonProperty("specialization_name")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonProperty("specialization_code")
    @Column(name = "code_lat", nullable = false, unique = true)
    private String code;

    @Column(name = "description", length = 3000)
    private String description;

    @JsonProperty("enable")
    @Column(name = "is_active",
            columnDefinition = "BOOLEAN DEFAULT true",
            insertable = false)
    private boolean isActive;

    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false)
    private LocalDateTime createdAt;
}
