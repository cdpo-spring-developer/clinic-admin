package com.springlessons.clinicadmin.examples.repository;

import com.springlessons.clinicadmin.examples.entity.AnimalShelter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AnimalShelterRepository extends CrudRepository<AnimalShelter, Integer> {
    AnimalShelter findByCodeAndIsActiveTrue(String code);
    Optional<AnimalShelter> findByName(String name);
    List<AnimalShelter> findByIsActiveTrue();
    List<AnimalShelter> findByIdIn(Collection<Integer> ids);

    @Query(nativeQuery = true, value = "SELECT sh.* FROM shelter sh " +
            "LEFT JOIN user_shelter us on sh.id = us.shelter_id " +
            "WHERE us.user_id IS NULL AND sh.is_active = true")
    List<AnimalShelter> selectSheltersWithoutUsers();
}
