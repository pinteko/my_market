package com.korsuk.my_market;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SessionFactoryUtils {

    private EntityManagerFactory factory;

    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
