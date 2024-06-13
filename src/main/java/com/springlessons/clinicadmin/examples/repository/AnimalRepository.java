package com.springlessons.clinicadmin.examples.repository;

import com.springlessons.clinicadmin.examples.entity.Animal;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByOwnerIdIsNotNullAndAnimalShelterId(int animalShelterId);

    Page<Animal> findByAnimalShelterCode(String animalShelterCode, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE animal SET owner_id = :ownerId " +
            "WHERE owner_id IS NULL AND id = :animalId")
    int setOwnerByAnimalId(int ownerId, int animalId);
}
