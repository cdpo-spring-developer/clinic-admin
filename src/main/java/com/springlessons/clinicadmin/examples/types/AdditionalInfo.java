package com.springlessons.clinicadmin.examples.types;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AdditionalInfo implements Serializable {
    private String rules;
    private LocalDate useBefore;
}
