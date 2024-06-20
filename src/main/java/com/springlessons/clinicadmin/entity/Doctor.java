package com.springlessons.clinicadmin.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "doctor") // user group
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "experience", nullable = false)
    private int experience;

    @Column(name = "is_active",
            columnDefinition = "BOOLEAN DEFAULT true",
            insertable = false)
    private boolean isActive;

    @ManyToMany
    @JoinTable(name = "doctor_specialization",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private List<Specialization> specializations = new ArrayList<>();

}

// один ко многим @OneToMany List / Set
// многие к одному @ManyToOne Specialization spec
// многие ко многим @ManyToMany List / Set
// один к одному @OneToOne Specialization spec