package com.korsuk.my_market.dao;

import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Novel;

import java.util.List;

public interface AuthorDao {
    Author findById(Long id);
    Author findByName(String name);
    List<Novel> findAll(Long id);

    void save(Author author);

    void update(Long id, String name, String surname);

    void deleteById(Long id);
}
