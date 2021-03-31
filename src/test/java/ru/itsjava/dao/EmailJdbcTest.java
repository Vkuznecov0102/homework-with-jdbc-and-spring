package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(EmailJdbcImpl.class)
public class EmailJdbcTest {

    @Autowired
    private EmailJdbc emailJdbc;

    Optional<Email> email = Optional.of(new Email(1L, "alexandro@protonmail.com"));
    Email objMail = email.get();

    @Test
    public void shouldHaveCorrectInsert() {
        emailJdbc.insertEmail(objMail);
        assertEquals(email, emailJdbc.getEmailById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        emailJdbc.insertEmail(objMail);
        assertEquals(email, emailJdbc.getEmailById(1L));
        objMail.setAddress("alexandro@gmail.com");
        emailJdbc.updateEmail(objMail);
        assertEquals(0, emailJdbc.countEmailByAddress("alexandro@protonmail.com"));
        assertEquals(1, emailJdbc.countEmailByAddress("alexandro@gmail.com"));
    }

    @Test
    public void shouldHaveCorrectDelete() {
        emailJdbc.insertEmail(objMail);
        assertEquals(email, emailJdbc.getEmailById(1L));
        assertNotNull(emailJdbc.getEmailById(1L));
        emailJdbc.deleteEmail(1L);
        assertEquals(emailJdbc.getEmailById(1L), Optional.empty());
    }
}
