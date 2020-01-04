package app.repository;

import app.domain.entity.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Cat entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public List<Cat> findAll() {
        return entityManager.createQuery("select c from Cat c", Cat.class).getResultList();
    }
}
