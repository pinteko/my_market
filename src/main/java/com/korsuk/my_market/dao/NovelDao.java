package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.products.Student;

import java.util.List;

public interface NovelDao {

    Novel findById(Long id);
    List<Novel> findByIdAuthor(Long id_author);
    List<Novel> findAll();

    void save(Novel novel);

    void update(Long id, String title, Long id_author);

    void deleteById(Long id);

    List<Student> studentsWhoRead(Long id_novel);
}
