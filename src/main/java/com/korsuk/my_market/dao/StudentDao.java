package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;

import java.util.List;

public interface StudentDao {
    Student findById(Long id);
    Student findByName(String name);
    List<Student> findAll();

    void save(Student student);

    void update(Long id, String name);

    void deleteById(Long id);

    List<Novel> readNovels(Long id_student);
}
