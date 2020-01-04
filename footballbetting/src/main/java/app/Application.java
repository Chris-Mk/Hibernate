package app;

import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {

        Persistence.createEntityManagerFactory("football_betting");
    }
}
