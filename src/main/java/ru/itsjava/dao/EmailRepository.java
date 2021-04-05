package ru.itsjava.dao;

import ru.itsjava.domains.Email;

import java.util.List;
import java.util.Optional;

public interface EmailRepository {
    long countEmailByAddress(String address);

    Optional<Email> getEmailById(long id);

    void insertEmail(Email email);

    void updateEmail(Email email);

    void deleteEmail(long id);

    List<Email> findAll();
}