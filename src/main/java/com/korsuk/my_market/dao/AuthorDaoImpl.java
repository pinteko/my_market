package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public AuthorDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Author findById(Long id) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Author author = em.find(Author.class, id);
// Подтверждаем транзакцию
            em.getTransaction().commit();
            return author;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author findByName(String name) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Author author = em.createQuery("select a from Author a where a.name = :name", Author.class)
                    .setParameter("name", name)
                    .getSingleResult();
            // Подтверждаем транзакцию
            em.getTransaction().commit();
            return author;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Novel> findAll(Long id) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            List<Novel> novels = em.createQuery("select n from Novel n where n.author.id = :id",
                    Novel.class).setParameter("id", id).getResultList();
// Подтверждаем транзакцию
            em.getTransaction().commit();
            return novels;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Author author) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id, String name, String surname) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Author author = em.getReference(Author.class, id);
            author.setName(name);
            author.setSurname(surname);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Author author = findById(id);
            em.remove(author);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
