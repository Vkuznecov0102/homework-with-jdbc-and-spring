package ru.itsjava.services;

import ru.itsjava.domains.Pet;

public interface PetService {
    int countPetByType(String type);

    Pet getPetById(long id);

    void insertPet(Pet pet);

    void updatePet(Pet pet);

    void deletePet(long id);
}
