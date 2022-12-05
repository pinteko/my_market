package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.StudentDto;
import com.korsuk.my_market.entities.StudentEntity;
import com.korsuk.my_market.repo.StudentRepository;
import com.korsuk.my_market.soap.students.Student;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

//    public Student getStudent(int id){
//        return studentRepository.findById(id);
//    }

    public StudentDto getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        StudentDto studentDto = new StudentDto(studentEntity.getId(), studentEntity.getName());
        return studentDto;
    }

    public List<StudentDto> getAllStudent(){
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentDto> studentsDto = studentEntities.stream().map(s -> new StudentDto(s.getId(),
                s.getName())).collect(Collectors.toList());
        return studentsDto;
    }

    public StudentDto getStudentByName(String name) {
        StudentEntity studentEntity = studentRepository.findStudentByName(name);
        StudentDto studentDto = new StudentDto(studentEntity.getId(), studentEntity.getName());
        return studentDto;
    }


    @Transactional
    public StudentEntity saveStudent (@NonNull StudentEntity studentEntity) {
       StudentEntity savedStudentEntity = studentRepository.saveAndFlush(studentEntity);
       return savedStudentEntity;
    }

    @Transactional
    public StudentEntity editStudent (@NonNull StudentEntity studentEntity) {
        return studentRepository.saveAndFlush(studentEntity);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudentById(id);
    }

    public static final Function<StudentEntity, Student> functionEntityToSoap = se -> {
        Student s = new Student();
        s.setId(se.getId());
        s.setName(se.getName());
        s.setAge(se.getAge());
        s.setGroupTitle(se.getGroup().getTitle());
        return s;
    };

    public List<Student> getAllStudents() {
        return studentRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public Student getByName(String name) {
        return studentRepository.findByName(name).map(functionEntityToSoap).get();
    }
}
