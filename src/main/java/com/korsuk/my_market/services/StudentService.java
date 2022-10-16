package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.StudentDto;
import com.korsuk.my_market.products.Student;
import com.korsuk.my_market.repo.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

//    public Student getStudent(int id){
//        return studentRepository.findById(id);
//    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findStudentById(id);
        StudentDto studentDto = new StudentDto(student.getId(), student.getName());
        return studentDto;
    }

    public List<StudentDto> getAllStudent(){
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentsDto = students.stream().map(s -> new StudentDto(s.getId(),
                s.getName())).collect(Collectors.toList());
        return studentsDto;
    }

    public StudentDto getStudentByName(String name) {
        Student student = studentRepository.findStudentByName(name);
        StudentDto studentDto = new StudentDto(student.getId(), student.getName());
        return studentDto;
    }


    @Transactional
    public Student saveStudent (@NonNull Student student) {
       Student savedStudent = studentRepository.saveAndFlush(student);
       return savedStudent;
    }

    @Transactional
    public Student editStudent (@NonNull Student student) {
        return studentRepository.saveAndFlush(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudentById(id);
    }
//    public void addStudent(String name) {
//        studentRepository.addStudent(name);
//    }
//
//    public void deleteStudent(int id) {
//        studentRepository.deleteStudent(id);
//    }
//
//    public void updateStudent(int id, String name) {
//        studentRepository.updateStudent(id, name);
//    }
}
