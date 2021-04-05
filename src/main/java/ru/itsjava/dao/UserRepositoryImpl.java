package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domains.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public long countUserByName(String fio) {
        return (long) entityManager.createQuery("select count(*) from User u where fio='" + fio + "'").getSingleResult();
    }

    @Override
    @Transactional
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        if (user.getId() == 0L) {
            entityManager.persist(user);
        }
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }


}
