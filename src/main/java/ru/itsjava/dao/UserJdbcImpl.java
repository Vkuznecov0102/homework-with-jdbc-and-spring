package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domains.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class UserJdbcImpl implements UserJdbc {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public long countUserByName(String fio) {
        return (long) entityManager.createQuery("select count(*) from User u where fio='"+fio+"'").getSingleResult();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(entityManager.find(User.class,id));
    }

    @Override
    public void insertUser(User user) {
        if(user.getId()==0L){
            entityManager.persist(user);
        }
        entityManager.merge(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        User user=entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }


}
