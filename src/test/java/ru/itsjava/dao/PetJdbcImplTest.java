package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domains.Pet;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(PetJdbcImpl.class)
public class PetJdbcImplTest {

    @Autowired
    private PetJdbc petJdbc;

    private final Pet pet = new Pet(1L, "Капибара", "Чучундра");

    @Test
    public void shouldHaveCorrectInsert() {
        petJdbc.insertPet(pet);
        assertEquals(pet, petJdbc.getPetById(1L));
    }

    //метод будет работать если снять final с полей класса

//    @Test
//    public void shouldHaveCorrectUpdate(){
//        Pet pet=new Pet(1L,"Капибара","Чучундра");
//        petJdbc.insertPet(pet);
//        Assertions.assertEquals(pet,petJdbc.getPetById(1L));
//        pet.setType("rat");
//        petJdbc.updatePet(pet);
//        Assertions.assertEquals(petJdbc.countPetByType("rat"),1);
//        Assertions.assertEquals(petJdbc.countPetByType("Капибара"),0);
//    }

    @Test
    public void shouldHaveCorrectDelete() {
        petJdbc.insertPet(pet);
        assertAll(
                () -> assertEquals(pet, petJdbc.getPetById(1L)),
                () -> assertNotNull(petJdbc.getPetById(1L))
        );
        petJdbc.deletePet(1L);
        assertThrows(EmptyResultDataAccessException.class, () -> petJdbc.getPetById(1L));
    }
}
