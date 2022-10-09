package com.korsuk.my_market;

import com.korsuk.my_market.products.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentDaoImpl implements StudentDao{

    private SessionFactoryUtils sessionFactoryUtils;

    public StudentDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Student findById(Integer id) {
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
    public void update(Integer id, String name) {
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
    public void deleteById(Integer id) {
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
}
