package com.korsuk.my_market.repo;

import com.korsuk.my_market.products.Student;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAll();

    Student findStudentById(Long id);

    Student findStudentByName(String name);

    void deleteStudentById(Long id);

//    @NonNull
//    Student saveAndFlush (Student student);
}
