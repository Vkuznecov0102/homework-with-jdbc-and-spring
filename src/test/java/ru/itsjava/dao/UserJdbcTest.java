package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import({UserJdbcImpl.class, PetJdbcImpl.class, EmailJdbcImpl.class})
public class UserJdbcTest {

    @Autowired
    private UserJdbc userJdbc;

    @Autowired
    private EmailJdbc emailJdbc;

    @Autowired
    private PetJdbc petJdbc;

    Optional<Pet> pet = Optional.of(new Pet(1L, "Капибара", "Чучундра"));
    Pet objPet = pet.get();

    Optional<Email> email = Optional.of(new Email(1L, "alexandro@protonmail.com"));
    Email objMail = email.get();

    Optional<User> user = Optional.of(new User(1L, "Измайлов ЛО", new Email(objMail.getId(), objMail.getAddress()), new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
    User objUser = user.get();

    @Test
    public void shouldHaveCorrectInsert() {

        petJdbc.insertPet(objPet);
        assertEquals(pet, petJdbc.getPetById(1L));

        emailJdbc.insertEmail(objMail);
        assertEquals(email, emailJdbc.getEmailById(1L));

        userJdbc.insertUser(objUser);
        assertEquals(user, userJdbc.getUserById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        petJdbc.insertPet(objPet);

        emailJdbc.insertEmail(objMail);

        userJdbc.insertUser(objUser);

        objUser.setFio("Александров ЛО");
        userJdbc.updateUser(objUser);
        assertEquals(1, userJdbc.countUserByName("Александров ЛО"));
        assertEquals(0, userJdbc.countUserByName("Измайлов ЛО"));

    }

    @Test
    public void shouldHaveCorrectDelete() {
        petJdbc.insertPet(objPet);

        emailJdbc.insertEmail(objMail);

        userJdbc.insertUser(objUser);

        assertEquals(user, userJdbc.getUserById(1L));
        assertNotNull(userJdbc.getUserById(1L));
        userJdbc.deleteUser(1L);
        assertEquals(userJdbc.getUserById(1L), Optional.empty());
    }
}
