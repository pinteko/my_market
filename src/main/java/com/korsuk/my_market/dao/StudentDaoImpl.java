package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDaoImpl implements StudentDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public StudentDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Student findById(Long id) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
// Подтверждаем транзакцию
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findByName(String name) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            Student student = em.createQuery("select s from Student s where s.name = :name", Student.class)
                    .setParameter("name", name)
                    .getSingleResult();
            // Подтверждаем транзакцию
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            List<Student> students = em.createQuery("select s from Student s").getResultList();
// Подтверждаем транзакцию
            em.getTransaction().commit();
            return students;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id, String name) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
//            em.createQuery("update Student s set s.name = :name where s.id = :id", Student.class)
//                    .setParameter("name", student.getName())
//                    .setParameter("id", student.getId())
//                    .executeUpdate();
//            em.getTransaction().commit();
            Student student = em.getReference(Student.class, id);
            student.setName(name);
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
            Student student = findById(id);
            em.remove(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Novel> readNovels(Long id_student) {
        try {
            EntityManager em = sessionFactoryUtils.getEntityManager();
            em.getTransaction().begin();
           Student student = findById(id_student);
            List<Novel> novels = student.getNovels();
//            List<Novel> novelList = em.createQuery("FROM Student WHERE id = :d")
//                    .setParameter("id", id_student).getResultList();
//            for (Novel novel: novelList) {
//                novel.getStudents();
//            }
            em.getTransaction().commit();
            return novels;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
