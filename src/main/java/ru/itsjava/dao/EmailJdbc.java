package ru.itsjava.dao;

import ru.itsjava.domains.Email;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface EmailJdbc {
    BigInteger countEmailByAddress(String address);

    Optional<Email> getEmailById(long id);

    void insertEmail(Email email);

    void updateEmail(Email email);

    void deleteEmail(long id);

    List<Email> findAll();
}
