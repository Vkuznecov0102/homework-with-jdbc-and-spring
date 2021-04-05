package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.UserRepository;
import ru.itsjava.domains.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public long countUserByName(String fio) {
        return userRepository.countUserByName(fio);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
