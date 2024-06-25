package com.springlessons.clinicadmin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springlessons.clinicadmin.entity.Specialization;

public record SpecializationDtoResponse(
        Specialization specialization,
        @JsonProperty("number_of_active_doctors") int numberOfActiveDoctors,
        @JsonProperty("number_of_inactive_doctors") int numberOfInactiveDoctors
) {
}
