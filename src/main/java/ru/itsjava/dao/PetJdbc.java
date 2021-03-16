package ru.itsjava.dao;

import ru.itsjava.domains.Pet;

public interface PetJdbc {
    int countPetByType(String type);

    Pet getPetById(long id);

    void insertPet(Pet pet);

    void updatePet(Pet pet);

    void deletePet(long id);
}
