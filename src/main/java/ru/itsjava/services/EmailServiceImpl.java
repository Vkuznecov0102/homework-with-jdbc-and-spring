package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.EmailRepository;
import ru.itsjava.domains.Email;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Override
    public long countEmailByAddress(String address) {
        return emailRepository.countEmailByAddress(address);
    }

    @Override
    public Optional<Email> getEmailById(long id) {
        return emailRepository.getEmailById(id);
    }

    @Override
    public void insertEmail(Email email) {
        emailRepository.insertEmail(email);
    }

    @Override
    public void updateEmail(Email email) {
        emailRepository.updateEmail(email);
    }

    @Override
    public void deleteEmail(long id) {
        emailRepository.deleteEmail(id);
    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }
}
