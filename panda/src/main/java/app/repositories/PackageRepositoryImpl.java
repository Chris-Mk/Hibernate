package app.repositories;

import app.domain.entities.Package;
import app.domain.entities.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {
    private final EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Package findById(Long id) {
        return entityManager.find(Package.class, id);
    }

    @Override
    public List<Package> findAll() {
        return entityManager.createQuery("select p from Package p", Package.class)
                .getResultList();
    }

    @Override
    public void save(Package entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public void updateStatus(Long id, Status status) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Package aPackage = findById(id);
        aPackage.setStatus(status);
        entityManager.persist(aPackage);
        transaction.commit();
    }

    @Override
    public void delete(Package entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Package aPackage = findById(entity.getId());
        entityManager.remove(aPackage);
        transaction.commit();
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(p) from Package p", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Package> findAllWithStatus(Status status) {
        return entityManager.createQuery("select p from Package p where p.status = :status", Package.class)
                .setParameter("status", status)
                .getResultList();
    }
}
