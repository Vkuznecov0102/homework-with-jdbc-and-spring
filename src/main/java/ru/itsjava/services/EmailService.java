package ru.itsjava.services;

import ru.itsjava.domains.Email;

public interface EmailService {
    int countEmailByAddress(String address);

    Email getEmailById(long id);

    void insertEmail(Email email);

    void updateEmail(Email email);

    void deleteEmail(long id);
}
