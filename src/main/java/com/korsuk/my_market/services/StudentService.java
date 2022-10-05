package com.korsuk.my_market.services;

import com.korsuk.my_market.products.Student;
import com.korsuk.my_market.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudent(int id){
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudent(){
        return studentRepository.getStudents();
    }

    public void addStudent(String name) {
        studentRepository.addStudent(name);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }

    public void updateStudent(int id, String name) {
        studentRepository.updateStudent(id, name);
    }
}
