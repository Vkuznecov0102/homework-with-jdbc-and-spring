package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.dao.PetJdbc;
import ru.itsjava.domains.Pet;

import java.math.BigInteger;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetJdbc petJdbc;

    @Override
    public BigInteger countPetByType(String type) {
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
}
