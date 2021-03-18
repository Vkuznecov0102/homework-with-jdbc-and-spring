package ru.itsjava.services;

import ru.itsjava.domains.User;

import java.util.Optional;

public interface UserService {
    long countUserByName(String fio);

    Optional<User> getUserById(long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
