package app.repositories;

import app.domain.entities.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {
    private final EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Receipt findById(Long id) {
        return entityManager.find(Receipt.class, id);
    }

    @Override
    public List<Receipt> findAll() {
        return entityManager.createQuery("select r from Receipt r", Receipt.class)
                .getResultList();
    }

    @Override
    public void save(Receipt entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public void updateStatus(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(id);
        transaction.commit();
    }

    @Override
    public void delete(Receipt entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Receipt receipt = findById(entity.getId());
        entityManager.remove(receipt);
        transaction.commit();
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(r) from Receipt r", Long.class)
                .getSingleResult();
    }
}
