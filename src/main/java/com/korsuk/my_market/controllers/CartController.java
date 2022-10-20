package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.services.CartService;
import com.korsuk.my_market.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final NovelService novelService;


    @GetMapping()
    public List<NovelDto> getNovels() {
        return cartService.novelsInCart();
    }

    @DeleteMapping("/delete_novel")
    public void deleteBook(@RequestParam Long novel_id){
//        novelService.getNovelById(novel_id);
        cartService.deleteFromCart(novel_id);
    }



}
