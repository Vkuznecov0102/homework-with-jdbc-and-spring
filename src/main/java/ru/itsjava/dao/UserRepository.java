package ru.itsjava.dao;

import ru.itsjava.domains.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    long countUserByName(String fio);

    Optional<User> getUserById(long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    List<User> findAll();
}
