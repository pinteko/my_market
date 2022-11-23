package com.korsuk.my_market;

//import com.korsuk.my_market.dao.*;
//import com.korsuk.my_market.products.Student;
//import com.korsuk.my_market.services.LibraryService;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class WorkWithDataBase {

//    private static SessionFactoryUtils sessionFactoryUtils;
//    private static StudentDaoImpl studentDao;
//    private static NovelDaoImpl novelDao;
//    private static AuthorDaoImpl authorDao;
//
//    private static LibraryService libraryService;
//
//    public WorkWithDataBase(SessionFactoryUtils sessionFactoryUtils, StudentDaoImpl studentDao,
//                            NovelDaoImpl novelDao, AuthorDaoImpl authorDao, LibraryService libraryService) {
//        this.sessionFactoryUtils = sessionFactoryUtils;
//        this.studentDao = studentDao;
//        this.novelDao = novelDao;
//        this.authorDao = authorDao;
//        this.libraryService = libraryService;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(WorkWithDataBase.class, args);
//        try {
////        classwork();
//
//         Student student = studentDao.findById(1L);
//            System.out.println(libraryService.getReadNovels(student));
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            sessionFactoryUtils.shutdown();
//        }
//    }
//
//    public static void classwork() {
//                SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
//        sessionFactoryUtils.init();
//            StudentDao studentDao = new StudentDaoImpl(sessionFactoryUtils);
//            AuthorDaoImpl authorDao = new AuthorDaoImpl(sessionFactoryUtils);
//            NovelDaoImpl novelDao = new NovelDaoImpl(sessionFactoryUtils);
////            studentDao.findById(1);
////            studentDao.findAll();
////            studentDao.findByName("Bob");
////            System.out.println(authorDao.findById(author.getId()));
////                        Novel novel = new Novel();
////            novel.setTitle("The catcher in the rye");
////            novel.setAuthor(authorDao.findById(7L));
////            novelDao.save(novel);
////                        Author author = new Author();
////            author.setName("Jerome");
////            author.setSurname("Salinger");
////            authorDao.save(author);
//    }
}
