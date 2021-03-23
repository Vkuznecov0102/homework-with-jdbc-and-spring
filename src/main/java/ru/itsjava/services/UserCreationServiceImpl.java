package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

import java.util.Scanner;

@RequiredArgsConstructor
@Service
@Transactional
public class UserCreationServiceImpl implements UserCreationService {

    private final EmailService emailService;

    private final PetService petService;

    private final UserService userService;

    @SneakyThrows
    @Override
    public void userCreation() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int count = 0;
            System.out.println("Добро пожаловать!Для добавления пользователя введите e-mail");

            String address = scanner.nextLine();
            Email email = new Email(address);
            emailService.insertEmail(email);

            System.out.println("Введите вид животного пользователя");
            Pet pet = new Pet();
            String type = scanner.nextLine();
            pet.setType(type);
            System.out.println("Введите кличку животного");
            String name = scanner.nextLine();
            pet.setName(name);
            petService.insertPet(new Pet(type, name));

            System.out.println("Введите имя пользователя");
            String userName = scanner.nextLine();
            User user = new User(5L, userName, new Email(5L, address), new Pet(5L, type, name));
            userService.insertUser(user);
            System.out.println("Пользователь успешно создан и добавлен!");
            count++;
            if (count > 1) {
                email.setId(user.getId() + 1L);
                pet.setId(user.getId() + 1L);
            }
            Console.main();
        }

    }
}
