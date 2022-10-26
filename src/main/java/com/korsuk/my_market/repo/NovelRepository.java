package com.korsuk.my_market.repo;

import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Novel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long>, JpaSpecificationExecutor<Novel> {

    @Query("Select n from Novel n order by n.id")
    List<Novel> findAll();

    Novel findNovelByAuthor(Author author);

    Novel findNovelById(Long id);

    Novel findNovelByTitle(String title);

    void deleteById(Long id);
    boolean existsNovelByTitle(String title);

}
