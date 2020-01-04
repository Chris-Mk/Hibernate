import entities.BasicLabel;
import entities.ingredients.BasicIngredient;
import entities.ingredients.Mint;
import entities.ingredients.Nettle;
import entities.ingredients.chemicalIngredients.AmmoniumChloride;
import entities.shampoos.BasicShampoo;
import entities.shampoos.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shampoo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Set<BasicIngredient> basicIngredients = new HashSet<>(){{
            add(new AmmoniumChloride());
            add(new Mint());
            add(new Nettle());
        }};

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "contains mint and nettle");

        BasicShampoo shampoo = new FreshNuke(label);
        shampoo.setIngredients(basicIngredients);

        entityManager.persist(shampoo);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
