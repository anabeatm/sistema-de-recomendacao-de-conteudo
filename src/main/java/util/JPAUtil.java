package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// a class to centralize the creation of EntityManager

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("recommendation-jpa");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
