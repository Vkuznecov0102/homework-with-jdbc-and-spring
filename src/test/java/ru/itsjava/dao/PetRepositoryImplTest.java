package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Pet;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(PetRepositoryImpl.class)
public class PetRepositoryImplTest {

    @Autowired
    private PetRepository petRepository;

    private final Optional<Pet> pet = Optional.of(new Pet(1L, "Капибара", "Чучундра"));
    private final Pet objPet = pet.get();

    @Test
    public void shouldHaveCorrectInsert() {
        petRepository.insertPet(objPet);
        assertEquals(pet, petRepository.getPetById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        petRepository.insertPet(objPet);
        assertEquals(pet, petRepository.getPetById(1L));
        objPet.setType("rat");
        petRepository.updatePet(objPet);
        assertAll(
                () -> assertEquals(1, petRepository.countPetByType("rat")),
                () -> assertEquals(0, petRepository.countPetByType("Капибара"))
        );
    }

    @Test
    public void shouldHaveCorrectDelete() {
        petRepository.insertPet(objPet);
        assertAll(
                () -> assertEquals(petRepository.getPetById(1L), pet),
                () -> assertNotNull(petRepository.getPetById(1L))
        );
        petRepository.deletePet(1L);
        assertEquals(Optional.empty(), petRepository.getPetById(1L));
    }
}
