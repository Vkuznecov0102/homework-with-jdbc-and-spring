package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({UserJdbcImpl.class, PetJdbcImpl.class, EmailJdbcImpl.class})
public class UserJdbcTest {

    @Autowired
    private UserJdbc userJdbc;

    @Autowired
    private EmailJdbc emailJdbc;

    @Autowired
    private PetJdbc petJdbc;

    Pet pet = new Pet(1L, "Капибара", "Чучундра");
    Email mail = new Email(1L, "leoleo@gmail.com");
    User user = new User(1L, "Измайлов ЛО",
            new Email(mail.getId(), mail.getAddress()),
            new Pet(pet.getId(), pet.getType(), pet.getName()));

    @Test
    public void shouldHaveCorrectInsert() {
        petJdbc.insertPet(pet);
        assertEquals(pet, petJdbc.getPetById(1L));
        emailJdbc.insertEmail(mail);
        assertEquals(mail, emailJdbc.getEmailById(1L));
        userJdbc.insertUser(user);
        assertEquals(user, userJdbc.getUserById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        petJdbc.insertPet(pet);
        emailJdbc.insertEmail(mail);
        userJdbc.insertUser(user);
        user.setFio("Александров ЛО");
        userJdbc.updateUser(user);
        assertAll(() ->
                        assertEquals(1, userJdbc.countUserByName("Александров ЛО")),
                () -> assertEquals(0, userJdbc.countUserByName("Измайлов ЛО")
                ));
    }

    @Test
    public void shouldHaveCorrectDelete() {
        petJdbc.insertPet(pet);
        emailJdbc.insertEmail(mail);
        userJdbc.insertUser(user);
        assertEquals(user, userJdbc.getUserById(1L));
        assertNotNull(userJdbc.getUserById(1L));
        userJdbc.deleteUser(1L);
        assertThrows(EmptyResultDataAccessException.class, () -> userJdbc.getUserById(1L));
    }
}
