package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

import java.math.BigInteger;
import java.util.Optional;

@DataJpaTest
@Import({UserJdbcImpl.class, PetJdbcImpl.class, EmailJdbcImpl.class})
public class UserJdbcTest {

    @Autowired
    private UserJdbc userJdbc;

    @Autowired
    private EmailJdbc emailJdbc;

    @Autowired
    private PetJdbc petJdbc;

    @Test
    public void shouldHaveCorrectInsert() {
        Optional<Pet> pet=Optional.of(new Pet(1L,"Капибара","Чучундра"));
        Pet objPet=pet.get();
        petJdbc.insertPet(objPet);
        Assertions.assertEquals(pet,petJdbc.getPetById(1L));

        Optional<Email> email= Optional.of(new Email(1L, "alexandro@protonmail.com"));
        Email objMail=email.get();
        emailJdbc.insertEmail(objMail);
        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));

        Optional<User> user = Optional.of(new User(1L, "Измайлов ЛО", new Email(objMail.getId(), objMail.getAddress()), new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
        User objUser=user.get();
        userJdbc.insertUser(objUser);
        Assertions.assertEquals(user, userJdbc.getUserById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Optional<Pet> pet=Optional.of(new Pet(1L,"Капибара","Чучундра"));
        Pet objPet=pet.get();
        petJdbc.insertPet(objPet);

        Optional<Email> email= Optional.of(new Email(1L, "alexandro@protonmail.com"));
        Email objMail=email.get();
        emailJdbc.insertEmail(objMail);

        Optional<User> user = Optional.of(new User(1L, "Измайлов ЛО", new Email(objMail.getId(), objMail.getAddress()), new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
        User objUser=user.get();
        userJdbc.insertUser(objUser);

        objUser.setFio("Александров ЛО");
        userJdbc.updateUser(objUser);
        BigInteger zero=new BigInteger("0");
        BigInteger one= new BigInteger("1");
        Assertions.assertEquals(userJdbc.countUserByName("Александров ЛО"), one);
        Assertions.assertEquals(userJdbc.countUserByName("Измайлов ЛО"), zero);

    }

    @Test
    public void shouldHaveCorrectDelete() {
        Optional<Pet> pet=Optional.of(new Pet(1L,"Капибара","Чучундра"));
        Pet objPet=pet.get();
        petJdbc.insertPet(objPet);

        Optional<Email> email= Optional.of(new Email(1L, "alexandro@protonmail.com"));
        Email objMail=email.get();
        emailJdbc.insertEmail(objMail);

        Optional<User> user = Optional.of(new User(1L, "Измайлов ЛО", new Email(objMail.getId(), objMail.getAddress()), new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
        User objUser=user.get();
        userJdbc.insertUser(objUser);

        Assertions.assertEquals(user, userJdbc.getUserById(1L));
        Assertions.assertNotNull(userJdbc.getUserById(1L));
        userJdbc.deleteUser(1L);
        Assertions.assertEquals(userJdbc.getUserById(1L),Optional.empty());
    }
}
