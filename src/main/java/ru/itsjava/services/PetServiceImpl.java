package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetJdbc;
import ru.itsjava.domains.Pet;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetJdbc petJdbc;

    @Override
    public long countPetByType(String type) {
        return petJdbc.countPetByType(type);
    }

    @Override
    public Optional<Pet> getPetById(long id) {
        return petJdbc.getPetById(id);
    }

    @Override
    public void insertPet(Pet pet) {
        petJdbc.insertPet(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        petJdbc.updatePet(pet);
    }

    @Override
    public void deletePet(long id) {
        petJdbc.deletePet(id);
    }

    @Override
    public List<Pet> findAll() {
        return petJdbc.findAll();
    }
}
