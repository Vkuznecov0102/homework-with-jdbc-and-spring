package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.dao.EmailJdbc;
import ru.itsjava.dao.EmailJdbcImpl;
import ru.itsjava.services.*;

import java.sql.SQLException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws SQLException {
        var context = SpringApplication.run(Application.class);
        context.getBean(UserCreationService.class).userCreation();
//        Console.main(args);
    }
}
