package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domains.Email;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class EmailJdbcImpl implements EmailJdbc {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public long countEmailByAddress(String address) {
        return (Long) entityManager.createNativeQuery("select count(*) from Email e where address='"+address+"'").getSingleResult();
    }

    @Override
    public Optional<Email> getEmailById(long id) {
       return Optional.ofNullable(entityManager.find(Email.class,id));
    }

    @Override
    public void insertEmail(Email email) {
        this.entityManager.persist(email);
    }

    @Override
    public void updateEmail(Email email) {
        entityManager.merge(email);
    }

    @Override
    public void deleteEmail(long id) {
        Email email=entityManager.find(Email.class,id);
        entityManager.remove(email);
    }

    @Override
    public List<Email> findAll() {
        return entityManager.createNativeQuery("select id,address from email").getResultList();
    }
}
