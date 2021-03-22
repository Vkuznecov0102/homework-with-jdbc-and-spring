package ru.itsjava.services;

import ru.itsjava.domains.Email;

import java.math.BigInteger;
import java.util.Optional;

public interface EmailService {
    BigInteger countEmailByAddress(String address);

    Optional<Email> getEmailById(long id);

    void insertEmail(Email email);

    void updateEmail(Email email);

    void deleteEmail(long id);
}
