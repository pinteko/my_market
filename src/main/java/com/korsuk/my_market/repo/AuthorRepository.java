package com.korsuk.my_market.repo;

import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();

    Author findAuthorById(Long id);

    Author findAuthorByName(String name);

    Author findAuthorBySurname(String surname);

}
