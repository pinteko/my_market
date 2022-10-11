package com.korsuk.my_market;

import com.korsuk.my_market.products.Student;

import java.util.List;

public interface StudentDao {
    Student findById(Integer id);
    Student findByName(String name);
    List<Student> findAll();

    void save(Student student);

    void update(Integer id, String name);

    void deleteById(Integer id);
}
