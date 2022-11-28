package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.dto.CartNotEntity;
import com.korsuk.my_market.dto.OrderItemDto;
import com.korsuk.my_market.services.CartService;
import com.korsuk.my_market.services.NovelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@Slf4j
public class CartController {

    private final CartService cartService;
    private final NovelService novelService;


//    @GetMapping()
//    public List<NovelDto> getNovels() {
//        return cartService.novelsInCart();
//    }

    @GetMapping
    public CartNotEntity getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/dto")
    public List<OrderItemDto> getNovelsNotEntity() {
        return cartService.getCurrentCart().getNovelsInCart();
    }

    @DeleteMapping("/delete_novel")
    public void deleteBook(@RequestParam Long novel_id){
//        novelService.getNovelById(novel_id);
        cartService.deleteFromCart(novel_id);
    }

    @GetMapping("/add_novel")
    public void addBook(@RequestParam Long novel_id){
//        novelService.getNovelById(novel_id);
        cartService.addNovelInCart(novel_id);
    }

    @GetMapping("/clear_cart")
    public void clearCart() {

    }

    @GetMapping("/create_order")
    public void createOrder() {

    }

}
