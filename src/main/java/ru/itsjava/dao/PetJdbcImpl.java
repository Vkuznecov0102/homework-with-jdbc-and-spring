package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domains.Pet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class PetJdbcImpl implements PetJdbc {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public long countPetByType(String type) {
        return (Long) entityManager.createNativeQuery("select count(*) from Pet p where type='"+type+"'").getSingleResult();
    }

    @Override
    public Optional<Pet> getPetById(long id) {
        return Optional.ofNullable(entityManager.find(Pet.class,id));
    }

    @Override
    public void insertPet(Pet pet) {
        this.entityManager.persist(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        entityManager.merge(pet);
    }

    @Override
    public void deletePet(long id) {
       Pet pet=entityManager.find(Pet.class,id);
       entityManager.remove(pet);
    }
}
