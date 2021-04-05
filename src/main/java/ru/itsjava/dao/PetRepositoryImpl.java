package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domains.Pet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PetRepositoryImpl implements PetRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public long countPetByType(String type) {
        return (long) entityManager.createQuery("select count(*) from Pet p where type='" + type + "'").getSingleResult();
    }

    @Override
    @Transactional
    public Optional<Pet> getPetById(long id) {
        return Optional.ofNullable(entityManager.find(Pet.class, id));
    }

    @Override
    @Transactional
    public void insertPet(Pet pet) {
        if (pet.getId() == 0L) {
            entityManager.persist(pet);
        }
        entityManager.merge(pet);
    }

    @Override
    @Transactional
    public void updatePet(Pet pet) {
        entityManager.merge(pet);
    }

    @Override
    @Transactional
    public void deletePet(long id) {
        Pet pet = entityManager.find(Pet.class, id);
        entityManager.remove(pet);
    }

    @Override
    @Transactional
    public List<Pet> findAll() {
        Query query = entityManager.createQuery("SELECT p FROM Pet p");
        return query.getResultList();
    }
}
