package chushka.repository;

import chushka.domain.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    public ProductRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("chushka")
                .createEntityManager();
    }

    @Override
    public void save(Product entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Product findByName(String name) {
        return this.entityManager.createQuery("SELECT P FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Product> findAll() {
        return this.entityManager.createQuery("SELECT p FROM Product AS p", Product.class)
                .getResultList();
    }
}
