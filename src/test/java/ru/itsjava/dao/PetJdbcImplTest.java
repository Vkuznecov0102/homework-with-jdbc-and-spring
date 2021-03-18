package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domains.Pet;

@JdbcTest
@Import(PetJdbcImpl.class)
public class PetJdbcImplTest {

    @Autowired
    private PetJdbc petJdbc;

    @Test
    public void shouldHaveCorrectInsert(){
        Pet pet=new Pet(1L,"Капибара","Чучундра");
        petJdbc.insertPet(pet);
        Assertions.assertEquals(pet,petJdbc.getPetById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate(){
        Pet pet=new Pet(1L,"Капибара","Чучундра");
        petJdbc.insertPet(pet);
        Assertions.assertEquals(pet,petJdbc.getPetById(1L));
        pet.setType("rat");
        petJdbc.updatePet(pet);
        Assertions.assertEquals(petJdbc.countPetByType("rat"),1);
        Assertions.assertEquals(petJdbc.countPetByType("Капибара"),0);
    }

    @Test
    public void shouldHaveCorrectDelete(){
        Pet pet=new Pet(1L,"Капибара","Чучундра");
        petJdbc.insertPet(pet);
        Assertions.assertEquals(pet,petJdbc.getPetById(1L));
        Assertions.assertNotNull(petJdbc.getPetById(1L));
        petJdbc.deletePet(1L);
        Assertions.assertThrows(EmptyResultDataAccessException.class,()-> petJdbc.getPetById(1L));
    }
}
