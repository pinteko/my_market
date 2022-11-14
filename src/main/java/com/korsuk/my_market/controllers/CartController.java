package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.NovelDto;
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


    @GetMapping()
    public List<NovelDto> getNovels() {
        return cartService.novelsInCart();
    }

    @GetMapping("/dto")
    public Set<NovelDto> getNovelsNotEntity() {
        return cartService.novelsInCartNotEntity();
    }

    @DeleteMapping("/delete_novel")
    public void deleteBook(@RequestParam Long novel_id){
//        novelService.getNovelById(novel_id);
        NovelDto novelDto = new NovelDto(novelService.getNovelById(novel_id));
        log.info( "delete from cartController" + novelDto.toString());
        cartService.deleteFromCart(novelDto);
    }

    @GetMapping("/add_novel")
    public void addBook(@RequestParam Long novel_id){
//        novelService.getNovelById(novel_id);
        NovelDto novelDto = new NovelDto(novelService.getNovelById(novel_id));
        log.info("add from cartController" + novelDto.toString());
        cartService.addNovel(novelDto);
    }



}
