package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(EmailRepositoryImpl.class)
public class EmailRepositoryImplTest {

    @Autowired
    private EmailRepository emailRepository;

    private final Email objMail = new Email(1L, "alexandro@protonmail.com");
    private final Optional<Email> email = Optional.of(objMail);

    @Test
    public void shouldHaveCorrectInsert() {
        emailRepository.insertEmail(objMail);
        assertEquals(email, emailRepository.getEmailById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        emailRepository.insertEmail(objMail);
        assertEquals(email, emailRepository.getEmailById(1L));
        objMail.setAddress("alexandro@gmail.com");
        emailRepository.updateEmail(objMail);
        assertAll(
                () -> assertEquals(0, emailRepository.countEmailByAddress("alexandro@protonmail.com")),
                () -> assertEquals(1, emailRepository.countEmailByAddress("alexandro@gmail.com"))
        );
    }

    @Test
    public void shouldHaveCorrectDelete() {
        emailRepository.insertEmail(objMail);
        assertAll(
                () -> assertEquals(email, emailRepository.getEmailById(1L)),
                () -> assertNotNull(emailRepository.getEmailById(1L))
        );
        emailRepository.deleteEmail(1L);
        assertEquals(Optional.empty(), emailRepository.getEmailById(1L));
    }
}
