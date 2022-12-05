package com.korsuk.my_market.repo;

import com.korsuk.my_market.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findAll();

    StudentEntity findStudentById(Long id);

    StudentEntity findStudentByName(String name);

    @Query("select s from StudentEntity s where s.name = ?1")
    Optional<StudentEntity> findByName(String name);

    void deleteStudentById(Long id);

//    @NonNull
//    Student saveAndFlush (Student student);
}
