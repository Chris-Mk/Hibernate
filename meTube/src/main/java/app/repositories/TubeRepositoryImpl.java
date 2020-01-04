package app.repositories;

import app.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("meTube")
                .createEntityManager();
    }

    @Override
    public Optional<Tube> findByName(String tubeName) {
        return Optional.ofNullable(this.entityManager
                .createQuery("SELECT t FROM Tube AS t WHERE t.title = :tubeName", Tube.class)
                .setParameter("tubeName", tubeName)
                .getSingleResult());
    }

    @Override
    public void save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Tube> findById(String id) {
        Tube tube = this.entityManager.find(Tube.class, id);
        return Optional.ofNullable(tube);
//        return Optional.ofNullable(this.entityManager
//                .createQuery("SELECT t FROM Tube AS t WHERE t.id = :id", Tube.class)
//                .setParameter("id", id)
//                .getSingleResult());
    }

    @Override
    public void delete(Tube entity) {
        this.entityManager.getTransaction().begin();
        Tube tube = this.entityManager.find(Tube.class, entity.getId());
        this.entityManager.remove(tube);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager
                .createQuery("SELECT t FROM Tube AS t", Tube.class)
                .getResultList();
    }

    @Override
    public void update(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }
}
