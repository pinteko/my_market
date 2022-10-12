package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class NovelDaoImpl implements NovelDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public NovelDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Novel findById(Long id) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Novel novel = em.find(Novel.class, id);
// Подтверждаем транзакцию
            em.getTransaction().commit();
            return novel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Novel> findByIdAuthor(Long id_author) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            List<Novel> novels = em.createQuery("select n from Novel n where n.author.id = :id",
                    Novel.class).setParameter("id", id_author).getResultList();
// Подтверждаем транзакцию
            em.getTransaction().commit();
            return novels;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Novel> findAll() {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            List<Novel> novels = em.createQuery("select n from Novel n").getResultList();
// Подтверждаем транзакцию
            em.getTransaction().commit();
            return novels;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Novel novel) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            em.persist(novel);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id, String title, Long id_author) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Novel novel = em.getReference(Novel.class, id);
            novel.setTitle(title);
            Author author = em.getReference(Author.class, id_author);
            novel.setAuthor(author);
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
            Novel novel = findById(id);
            em.remove(novel);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> studentsWhoRead(Long id_novel) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Novel novel = findById(id_novel);
            List<Student> studentsWhoRead = novel.getStudents();
            em.getTransaction().commit();
            return studentsWhoRead;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
