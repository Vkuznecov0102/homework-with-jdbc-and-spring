package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.UserJdbc;
import ru.itsjava.domains.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserJdbc userJdbc;

    @Override
    public long countUserByName(String fio) {
        return userJdbc.countUserByName(fio);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userJdbc.getUserById(id);
    }

    @Override
    public void insertUser(User user) {
        userJdbc.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userJdbc.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userJdbc.deleteUser(id);
    }

    @Override
    public List<User> findAll() {
        return userJdbc.findAll();
    }
}
