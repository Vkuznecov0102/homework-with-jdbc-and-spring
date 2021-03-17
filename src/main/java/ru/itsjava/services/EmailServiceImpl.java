package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.EmailJdbc;
import ru.itsjava.domains.Email;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailJdbc emailJdbc;

    @Override
    public long countEmailByAddress(String address) {
        return emailJdbc.countEmailByAddress(address);
    }

    @Override
    public Optional<Email> getEmailById(long id) {
        return emailJdbc.getEmailById(id);
    }

    @Override
    public void insertEmail(Email email) {
        emailJdbc.insertEmail(email);
    }

    @Override
    public void updateEmail(Email email) {
        emailJdbc.updateEmail(email);
    }

    @Override
    public void deleteEmail(long id) {
        emailJdbc.deleteEmail(id);
    }
}
