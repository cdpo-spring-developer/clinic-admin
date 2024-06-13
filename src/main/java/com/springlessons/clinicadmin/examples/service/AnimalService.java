package com.springlessons.clinicadmin.examples.service;

import com.springlessons.clinicadmin.examples.dto.AnimalRequest;
import com.springlessons.clinicadmin.examples.entity.Animal;
import com.springlessons.clinicadmin.examples.entity.AnimalShelter;
import com.springlessons.clinicadmin.examples.exceptions.ShelterException;
import com.springlessons.clinicadmin.examples.repository.AnimalRepository;
import com.springlessons.clinicadmin.examples.repository.AnimalShelterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalShelterRepository animalShelterRepository;

    public AnimalService(AnimalRepository animalRepository, AnimalShelterRepository animalShelterRepository) {
        this.animalRepository = animalRepository;
        this.animalShelterRepository = animalShelterRepository;
    }

    public int createAnimal(AnimalRequest animalRequest) throws ShelterException {
        AnimalShelter animalShelter = animalShelterRepository.findById(animalRequest.shelterId())
                .orElseThrow(()->new ShelterException("Приют с ID " + animalRequest.shelterId() + " не найдет"));
        animalRequest.animal().setAnimalShelter(animalShelter);
        return animalRepository.save(animalRequest.animal()).getId();
    }

    public List<Animal> getAnimalsWithOwnersByShelterId(int shelterId) {
        return animalRepository.findByOwnerIdIsNotNullAndAnimalShelterId(shelterId);
    }

    public Page<Animal> getAnimalsByShelterCode(String shelterCode, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return animalRepository.findByAnimalShelterCode(shelterCode, pageable);
    }

    public void setOwnerToAnimal(int ownerId, int animalId) throws ShelterException {
        if (animalRepository.setOwnerByAnimalId(ownerId, animalId) == 0) {
            throw new ShelterException("Животному с ID = " + animalId +
                    " не удалось установить владельца с ID " + ownerId);
        }
    }
}
