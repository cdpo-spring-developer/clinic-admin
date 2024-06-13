package com.springlessons.clinicadmin.examples.controller;

import com.springlessons.clinicadmin.examples.dto.AnimalRequest;
import com.springlessons.clinicadmin.examples.entity.Animal;
import com.springlessons.clinicadmin.examples.exceptions.ShelterException;
import com.springlessons.clinicadmin.examples.service.AnimalService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController("/animals")
@Validated
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AnimalRequest animal) {

        try {
            return ResponseEntity
                    .created(
                            URI.create("http://localhost:8080/animals/" +
                                    animalService.createAnimal(animal)))
                    .build();
        } catch (ShelterException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/shelter/{id}")
    List<Animal> getAnimalsWithOwnersByShelterId(@PathVariable(name = "id") @Min(1) int shelterId) {
        return animalService.getAnimalsWithOwnersByShelterId(shelterId);
    }

    @GetMapping("/shelter")
    Page<Animal> getAnimalsByShelterCode(@RequestParam(name = "code") @Size(min = 3, max = 50) String shelterCode,
                                         @RequestParam @Min(0) int page, @RequestParam @Min(1) int size) {
        return animalService.getAnimalsByShelterCode(shelterCode, page, size);
    }

    @PutMapping("/{animalId}/owner/{ownerId}")
    public void setOwnerToAnimal( @PathVariable @Min(1) int ownerId, @PathVariable @Min(1) int animalId) {
        try {
            animalService.setOwnerToAnimal(ownerId, animalId);
        } catch (ShelterException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
