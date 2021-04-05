package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domains.Email;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class EmailRepositoryImpl implements EmailRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public long countEmailByAddress(String address) {
        return (long) entityManager.createQuery("select count(*) from Email e where address='" + address + "'").getSingleResult();
    }

    @Override
    @Transactional
    public Optional<Email> getEmailById(long id) {
        return Optional.ofNullable(entityManager.find(Email.class, id));
    }

    @Override
    @Transactional
    public void insertEmail(Email email) {
        if (email.getId() == 0L) {
            entityManager.persist(email);
        }
        entityManager.merge(email);
    }

    @Override
    @Transactional
    public void updateEmail(Email email) {
        entityManager.merge(email);
    }

    @Override
    @Transactional
    public void deleteEmail(long id) {
        Email email = entityManager.find(Email.class, id);
        entityManager.remove(email);
    }

    @Override
    @Transactional
    public List<Email> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM Email e");
        return query.getResultList();
    }
}