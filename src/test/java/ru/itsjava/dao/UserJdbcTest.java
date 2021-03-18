package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

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
        Pet pet = new Pet(1L, "Капибара", "Чучундра");
        petJdbc.insertPet(pet);
        Assertions.assertEquals(pet, petJdbc.getPetById(1L));
        Email mail = new Email(1L, "leoleo@gmail.com");
        emailJdbc.insertEmail(mail);
        Assertions.assertEquals(mail, emailJdbc.getEmailById(1L));
        User user = new User(1L, "Измайлов ЛО", new Email(mail.getId(), mail.getAddress()), new Pet(pet.getId(), pet.getType(), pet.getName()));
        userJdbc.insertUser(user);
        Assertions.assertEquals(user, userJdbc.getUserById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Pet pet = new Pet(1L, "Капибара", "Чучундра");
        petJdbc.insertPet(pet);
        Email mail = new Email(1L, "leoleo@gmail.com");
        emailJdbc.insertEmail(mail);
        User user = new User(1L, "Измайлов ЛО", new Email(mail.getId(), mail.getAddress()), new Pet(pet.getId(), pet.getType(), pet.getName()));
        userJdbc.insertUser(user);
        user.setFio("Александров ЛО");
        userJdbc.updateUser(user);
        Assertions.assertEquals(userJdbc.countUserByName("Александров ЛО"), 1);
        Assertions.assertEquals(userJdbc.countUserByName("Измайлов ЛО"), 0);

    }

    @Test
    public void shouldHaveCorrectDelete() {
        Pet pet = new Pet(1L, "Капибара", "Чучундра");
        petJdbc.insertPet(pet);
        Email mail = new Email(1L, "leoleo@gmail.com");
        emailJdbc.insertEmail(mail);
        User user = new User(1L, "Измайлов ЛО", new Email(mail.getId(), mail.getAddress()), new Pet(pet.getId(), pet.getType(), pet.getName()));
        userJdbc.insertUser(user);
        Assertions.assertEquals(user, userJdbc.getUserById(1L));
        Assertions.assertNotNull(userJdbc.getUserById(1L));
        userJdbc.deleteUser(1L);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> userJdbc.getUserById(1L));
    }
}
