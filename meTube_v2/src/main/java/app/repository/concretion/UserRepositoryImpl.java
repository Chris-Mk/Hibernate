package app.repository.concretion;

import app.domain.entities.Tube;
import app.domain.entities.User;
import app.repository.abstraction.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public int update(Collection<User> entities) {
        int count = 0;
        for (User entity : entities) {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();
            count++;
        }
        return count;
    }

    @Override
    public int delete(Collection<User> entities) {
        int count = 0;
        for (User entity : entities) {
            this.entityManager.getTransaction().begin();
            User user = this.entityManager.find(User.class, entity.getId());
            this.entityManager.remove(user);
            this.entityManager.getTransaction().commit();
            count++;
        }
        return count;
    }

    @Override
    public Collection<User> findAll() {
        return this.entityManager
                .createQuery("SELECT u FROM User AS u", User.class)
                .getResultList();
    }

    @Override
    public User findById(String id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public long count() {
        return this.entityManager
                .createQuery("SELECT count(u) FROM User AS u", long.class)
                .getSingleResult();
    }

    @Override
    public User findByUsernameAndEmail(String username, String email) {
        return this.entityManager
                .createQuery("SELECT u FROM User AS u WHERE u.username = :username AND u.email = :email", User.class)
                .setParameter("username", username)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        return this.entityManager
                .createQuery("SELECT u FROM User AS u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
        return this.entityManager
                .createQuery("SELECT u FROM User AS u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<Tube> findAllTubes(String username) {
        return this.entityManager
                .createQuery("SELECT u.tubes FROM User AS u WHERE u.username = :username", Tube.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return this.entityManager
                .createQuery("SELECT u FROM User u WHERE u.username = : username AND u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }
}
