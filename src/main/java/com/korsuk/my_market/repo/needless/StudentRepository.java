package com.korsuk.my_market.repo.needless;

import com.korsuk.my_market.products.Cart;
import com.korsuk.my_market.products.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class StudentRepository {
    private List<Student> students;
    private Integer id;

    @PostConstruct
    public void init() {
//        students = new ArrayList<>(Arrays.asList(
//                new Student(1, "Bob", new Cart()),
//                new Student(2, "Jonh", new Cart()),
//                new Student(3, "Dave", new Cart())
//        ));
//        id = 3;
    }

    public Student findById(int id) {
        return students.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
    }

    public List<Student> getStudents(){
        return students;
    }

    public void addStudent(String name) {
//        students.add(new Student(++id, name, new Cart()));
    }

    public void deleteStudent(int id) {
        if (id > 0 && id <= students.size()) {
        students.remove(students.get(id)); }
    }

    public void updateStudent(int id, String name) {
        if (id > 0 && id <= students.size()) {
        students.get(id).setName(name); }
    }
}
