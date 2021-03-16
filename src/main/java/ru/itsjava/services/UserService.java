package ru.itsjava.services;

import ru.itsjava.domains.User;

public interface UserService {
    int countUserByName(String fio);

    User getUserById(long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
