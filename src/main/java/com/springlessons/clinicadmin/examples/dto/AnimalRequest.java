package com.springlessons.clinicadmin.examples.dto;

import com.springlessons.clinicadmin.examples.entity.Animal;

public record AnimalRequest(Animal animal, int shelterId){
}
