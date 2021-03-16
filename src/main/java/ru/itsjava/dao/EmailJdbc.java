package ru.itsjava.dao;

import ru.itsjava.domains.Email;

public interface EmailJdbc {
    int countEmailByAddress(String address);

    Email getEmailById(long id);

    void insertEmail(Email email);

    void updateEmail(Email email);

    void deleteEmail(long id);
}
