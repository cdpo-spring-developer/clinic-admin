package com.springlessons.clinicadmin.examples.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springlessons.clinicadmin.examples.entity.Animal;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AnimalRequest(Animal animal, int shelterId){
}
