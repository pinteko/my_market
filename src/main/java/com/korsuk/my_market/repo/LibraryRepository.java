//package com.korsuk.my_market.repo;
//
//import com.korsuk.my_market.dao.NovelDaoImpl;
//import com.korsuk.my_market.dao.StudentDaoImpl;
//import com.korsuk.my_market.products.Novel;
//import com.korsuk.my_market.products.Student;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class LibraryRepository {
//
//    private StudentDaoImpl studentDao;
//    private NovelDaoImpl novelDao;
//
//    public LibraryRepository(StudentDaoImpl studentDao, NovelDaoImpl novelDao) {
//        this.studentDao = studentDao;
//        this.novelDao = novelDao;
//    }
//
//    public List<Novel> getReadNovels(Student student) {
//        return studentDao.readNovels(student.getId());
//    }
//
//
//    public List<Student> getStudentsWhoRead(Novel novel)  {
//        return novelDao.studentsWhoRead(novel.getId());
//    }
//}
