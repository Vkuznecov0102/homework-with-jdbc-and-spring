package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({UserRepositoryImpl.class, PetRepositoryImpl.class, EmailRepositoryImpl.class})
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private PetRepository petRepository;

    private final Optional<Pet> pet = Optional.of(new Pet(1L, "Капибара", "Чучундра"));
    private final Pet objPet = pet.get();

    private final Optional<Email> email = Optional.of(new Email(1L, "alexandro@protonmail.com"));
    private final Email objMail = email.get();

    private final Optional<User> user = Optional.of(new User(1L, "Измайлов ЛО", new Email(objMail.getId(), objMail.getAddress()), new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
    private final User objUser = user.get();

    @Test
    public void shouldHaveCorrectInsert() {

        petRepository.insertPet(objPet);
        assertEquals(pet, petRepository.getPetById(1L));

        emailRepository.insertEmail(objMail);
        assertEquals(email, emailRepository.getEmailById(1L));

        userRepository.insertUser(objUser);
        assertEquals(user, userRepository.getUserById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        petRepository.insertPet(objPet);

        emailRepository.insertEmail(objMail);

        userRepository.insertUser(objUser);

        objUser.setFio("Александров ЛО");
        userRepository.updateUser(objUser);
        assertAll(
                () -> assertEquals(1, userRepository.countUserByName("Александров ЛО")),
                () -> assertEquals(0, userRepository.countUserByName("Измайлов ЛО"))
        );

    }

    @Test
    public void shouldHaveCorrectDelete() {
        petRepository.insertPet(objPet);

        emailRepository.insertEmail(objMail);

        userRepository.insertUser(objUser);

        assertAll(
                () -> assertEquals(user, userRepository.getUserById(1L)),
                () -> assertNotNull(userRepository.getUserById(1L))
        );
        userRepository.deleteUser(1L);
        assertEquals(Optional.empty(), userRepository.getUserById(1L));
    }
}
