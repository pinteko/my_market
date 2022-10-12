package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class SessionFactoryUtils {

    private EntityManagerFactory factory;

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Novel.class)
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
