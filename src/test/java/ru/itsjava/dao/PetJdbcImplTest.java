package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Pet;

import java.math.BigInteger;
import java.util.Optional;

@DataJpaTest
@Import(PetJdbcImpl.class)
public class PetJdbcImplTest {

    @Autowired
    private PetJdbc petJdbc;

    @Test
    public void shouldHaveCorrectInsert(){
        Optional<Pet> pet=Optional.of(new Pet(1L,"Капибара","Чучундра"));
        Pet objPet=pet.get();
        petJdbc.insertPet(objPet);
        Assertions.assertEquals(pet,petJdbc.getPetById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate(){
        Optional<Pet> pet=Optional.of(new Pet(1L,"Капибара","Чучундра"));
        Pet objPet=pet.get();
        petJdbc.insertPet(objPet);
        Assertions.assertEquals(pet,petJdbc.getPetById(1L));
        objPet.setType("rat");
        petJdbc.updatePet(objPet);
        BigInteger zero=new BigInteger("0");
        BigInteger one= new BigInteger("1");
        Assertions.assertEquals(petJdbc.countPetByType("rat"),one);
        Assertions.assertEquals(petJdbc.countPetByType("Капибара"),zero);
    }

    @Test
    public void shouldHaveCorrectDelete(){
        Optional<Pet> pet=Optional.of(new Pet(1L,"Капибара","Чучундра"));
        Pet objPet=pet.get();
        petJdbc.insertPet(objPet);
        Assertions.assertEquals(pet,petJdbc.getPetById(1L));
        Assertions.assertNotNull(petJdbc.getPetById(1L));
        petJdbc.deletePet(1L);
        Assertions.assertEquals(petJdbc.getPetById(1L),Optional.empty());
    }
}
