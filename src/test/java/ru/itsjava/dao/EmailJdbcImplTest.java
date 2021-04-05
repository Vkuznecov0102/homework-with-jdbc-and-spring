package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domains.Email;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(EmailJdbcImpl.class)
public class EmailJdbcImplTest {

    @Autowired
    private EmailJdbc emailJdbc;

    Email email = new Email(1L, "alexandro@protonmail.com");


    @Test
    public void shouldHaveCorrectInsert() {
        emailJdbc.insertEmail(email);
        assertEquals(email, emailJdbc.getEmailById(1L));
    }

//метод будет работать если снять final с полей класса

//    @Test
//    public void shouldHaveCorrectUpdate(){
//        Email email=new Email(1L,"alexandro@protonmail.com");
//        emailJdbc.insertEmail(email);
//        Assertions.assertEquals(email,emailJdbc.getEmailById(1L));
//        email.setAddress("alexandro@mail.ru");
//        emailJdbc.updateEmail(email);
//        Assertions.assertEquals(emailJdbc.countEmailByAddress("alexandro@protonmail.com"),0);
//        Assertions.assertEquals(emailJdbc.countEmailByAddress("alexandro@mail.ru"),1);
//    }

    @Test
    public void shouldHaveCorrectDelete() {
        emailJdbc.insertEmail(email);
        assertEquals(email, emailJdbc.getEmailById(1L));
        assertNotNull(emailJdbc.getEmailById(1L));
        emailJdbc.deleteEmail(1L);
        assertThrows(EmptyResultDataAccessException.class, () -> emailJdbc.getEmailById(1L));
    }
}
