package app.repository.concretion;

import app.domain.entities.Tube;
import app.repository.abstraction.TubeRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public int update(Collection<Tube> entities) {
        int count = 0;
        for (Tube entity : entities) {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();
            count++;
        }
        return count;
    }

    @Override
    public int delete(Collection<Tube> entities) {
        int count = 0;
        for (Tube entity : entities) {
            this.entityManager.getTransaction().begin();
            Tube tube = this.entityManager.find(Tube.class, entity.getId());
            this.entityManager.remove(tube);
            this.entityManager.getTransaction().commit();
            count++;
        }
        return count;
    }

    @Override
    public Collection<Tube> findAll() {
        return this.entityManager
                .createQuery("SELECT t FROM Tube AS t", Tube.class)
                .getResultList();
    }

    @Override
    public Tube findById(String id) {
        return this.entityManager.find(Tube.class, id);
    }

    @Override
    public long count() {
        return this.entityManager
                .createQuery("SELECT count(t) FROM Tube AS t", long.class)
                .getSingleResult();
    }
}
