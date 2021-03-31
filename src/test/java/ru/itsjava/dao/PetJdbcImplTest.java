package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Pet;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(PetJdbcImpl.class)
public class PetJdbcImplTest {

    @Autowired
    private PetJdbc petJdbc;

    Optional<Pet> pet = Optional.of(new Pet(1L, "Капибара", "Чучундра"));
    Pet objPet = pet.get();

    @Test
    public void shouldHaveCorrectInsert() {
        petJdbc.insertPet(objPet);
        assertEquals(pet, petJdbc.getPetById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        petJdbc.insertPet(objPet);
        assertEquals(pet, petJdbc.getPetById(1L));
        objPet.setType("rat");
        petJdbc.updatePet(objPet);
        assertEquals(1, petJdbc.countPetByType("rat"));
        assertEquals(0, petJdbc.countPetByType("Капибара"));
    }

    @Test
    public void shouldHaveCorrectDelete() {
        petJdbc.insertPet(objPet);
        assertEquals(pet, petJdbc.getPetById(1L));
        assertNotNull(petJdbc.getPetById(1L));
        petJdbc.deletePet(1L);
        assertEquals(petJdbc.getPetById(1L), Optional.empty());
    }
}
