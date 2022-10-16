package com.korsuk.my_market.repo;

import com.korsuk.my_market.products.Cart;
import com.korsuk.my_market.products.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartById(Long id);

    List<Cart> findAll();

    List<Cart> findAllByDateBetween(Date start, Date end);

    void deleteCartByNovel_Id(Long id);
//
//    List<Novel> findAllNovel();
//
//    List<Novel> findAllByDateBetween(Date start, Date end);

}
