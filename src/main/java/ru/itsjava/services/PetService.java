package ru.itsjava.services;

import ru.itsjava.domains.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    long countPetByType(String type);

    Optional<Pet> getPetById(long id);

    void insertPet(Pet pet);

    void updatePet(Pet pet);

    void deletePet(long id);

    List<Pet> findAll();
}
