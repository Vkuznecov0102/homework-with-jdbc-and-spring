package ru.itsjava.dao;

import ru.itsjava.domains.User;

public interface UserJdbc {
    int countUserByName(String fio);

    User getUserById(long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
