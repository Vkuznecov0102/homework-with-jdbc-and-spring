package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;

import java.math.BigInteger;
import java.util.Optional;

@DataJpaTest
@Import(EmailJdbcImpl.class)
public class EmailJdbcTest {

    @Autowired
    private EmailJdbc emailJdbc;

    @Test
    public void shouldHaveCorrectInsert(){
        Optional<Email> email= Optional.of(new Email(1L, "alexandro@protonmail.com"));
        Email objMail=email.get();
        emailJdbc.insertEmail(objMail);
        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));

    }

    @Test
    public void shouldHaveCorrectUpdate(){
        Optional<Email> email= Optional.of(new Email(1L, "alexandro@protonmail.com"));
        Email objMail=email.get();
        emailJdbc.insertEmail(objMail);
        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));
        objMail.setAddress("alexandro@gmail.com");
        emailJdbc.updateEmail(objMail);
        BigInteger zero=new BigInteger("0");
        BigInteger one= new BigInteger("1");
        Assertions.assertEquals(emailJdbc.countEmailByAddress("alexandro@protonmail.com"), zero);
        Assertions.assertEquals(emailJdbc.countEmailByAddress("alexandro@gmail.com"),one);
    }

    @Test
    public void shouldHaveCorrectDelete(){
        Optional<Email> email= Optional.of(new Email(1L, "alexandro@protonmail.com"));
        Email objMail=email.get();
        emailJdbc.insertEmail(objMail);
        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));
        Assertions.assertNotNull(emailJdbc.getEmailById(1L));
        emailJdbc.deleteEmail(1L);
        Assertions.assertEquals(emailJdbc.getEmailById(1L),Optional.empty());
    }
}
