package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;

import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class UserCreationServiceImpl implements UserCreationService {


    private final EmailService emailService;
    private final PetService petService;
    private final UserService userService;

    @SneakyThrows
    @Override
    public void userCreation() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit=true;

        while (isExit) {
            System.out.println("Добро пожаловать!Для добавления пользователя введите e-mail");

            String address = scanner.nextLine();
            emailService.insertEmail(new Email(address));

            System.out.println("Введите вид животного пользователя");
            String type = scanner.nextLine();
            System.out.println("Введите кличку животного");
            String name = scanner.nextLine();
            petService.insertPet(new Pet(type, name));

            System.out.println("Введите имя пользователя");
            String userName = scanner.nextLine();
            User user = new User(0L, userName, new Email(0L, address), new Pet(0L, type, name));
            userService.insertUser(user);
            System.out.println("Пользователь успешно создан и добавлен!");

            System.out.println("Если хотите закончить нажмите ДА");
            if (scanner.nextLine().strip().equalsIgnoreCase("ДА")) {
                isExit = false;
            }
        }

    }
}
