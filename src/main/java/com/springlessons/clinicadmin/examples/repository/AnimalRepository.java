package com.springlessons.clinicadmin.examples.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springlessons.clinicadmin.examples.entity.Animal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    // Animal:
    // ownerId
    // animalShelter

    // AnimalShelter
    // id


    @Query(nativeQuery = true, value = "SELECT animal.id, animal.owner_id, animal.shelter_id, animal.name FROM animal WHERE owner_id IS NOT NULL AND shelter_id = :animalShelterId")
    List<Animal> getByOwnerIdIsNotNullAndAnimalShelterId(int animalShelterId);

    Page<Animal> findByAnimalShelterCode(String animalShelterCode,
                                         Pageable pageable);

    @Transactional
    @Modifying // замена jdbc[executeQuery] на jdbc[executeUpdate]
    @Query(nativeQuery = true, value = "UPDATE animal SET owner_id = :ownerId " +
            "WHERE id = :animalId")
    int setOwnerByAnimalId(int ownerId, int animalId);
}

