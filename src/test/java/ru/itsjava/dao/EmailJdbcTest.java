package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domains.Email;

@JdbcTest
@Import(EmailJdbcImpl.class)
public class EmailJdbcTest {

    @Autowired
    private EmailJdbc emailJdbc;

    @Test
    public void shouldHaveCorrectInsert(){
        Email email=new Email(1L,"alexandro@protonmail.com");
        emailJdbc.insertEmail(email);
        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));
    }

    @Test
    public void shouldHaveCorrectUpdate(){
        Email email=new Email(1L,"alexandro@protonmail.com");
        emailJdbc.insertEmail(email);
        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));
        email.setAddress("alexandro@mail.ru");
        emailJdbc.updateEmail(email);
        Assertions.assertEquals(emailJdbc.countEmailByAddress("alexandro@protonmail.com"),0);
        Assertions.assertEquals(emailJdbc.countEmailByAddress("alexandro@mail.ru"),1);
    }

    @Test
    public void shouldHaveCorrectDelete(){
        Email email=new Email(1L,"alexandro@protonmail.com");
        emailJdbc.insertEmail(email);
        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));
        Assertions.assertNotNull(emailJdbc.getEmailById(1L));
        emailJdbc.deleteEmail(1L);
        Assertions.assertThrows(EmptyResultDataAccessException.class,()-> emailJdbc.getEmailById(1L));
    }
}
