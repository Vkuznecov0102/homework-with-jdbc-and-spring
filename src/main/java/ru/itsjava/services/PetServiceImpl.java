package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetRepository;
import ru.itsjava.domains.Pet;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public long countPetByType(String type) {
        return petRepository.countPetByType(type);
    }

    @Override
    public Optional<Pet> getPetById(long id) {
        return petRepository.getPetById(id);
    }

    @Override
    public void insertPet(Pet pet) {
        petRepository.insertPet(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        petRepository.updatePet(pet);
    }

    @Override
    public void deletePet(long id) {
        petRepository.deletePet(id);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }
}
