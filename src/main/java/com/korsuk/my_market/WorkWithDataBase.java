package com.korsuk.my_market;

import com.korsuk.my_market.products.Student;

public class WorkWithDataBase {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            StudentDao studentDao = new StudentDaoImpl(sessionFactoryUtils);
//            studentDao.findById(1);
//            studentDao.findAll();
//            studentDao.findByName("Bob");
            studentDao.deleteById(4);
            System.out.println(studentDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}
