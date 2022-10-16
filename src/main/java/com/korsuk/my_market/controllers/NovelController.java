package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.products.Cart;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.services.CartService;
import com.korsuk.my_market.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/novels")
public class NovelController {

    private final NovelService novelService;
    private final CartService cartService;


    @GetMapping()
    public List<NovelDto> getNovels() {
        return novelService.getAllNovels();
    }

    @GetMapping("/{id}")
    public NovelDto show(@PathVariable("id") Long id) {
       return novelService.getNovelByIdDto(id);
    }


    @GetMapping("/change_rating")
    public void changeRating(@RequestParam Long novel_id, @RequestParam Double delta){
        novelService.changeRating(novel_id, delta);
    }

    @GetMapping("/add_cart")
    public void addCart(@RequestParam Long novel_id) {
        Cart cart = new Cart();
        cart.setNovel(novelService.getNovelById(novel_id));
        cart.setDate(new Date(System.currentTimeMillis()));
        cartService.save(cart);

    }

    @DeleteMapping("/delete_novel")
    public void deleteBook(@RequestParam Long novel_id){
        novelService.deleteBookById(novel_id);
    }


    @GetMapping("/new_novel")
    public String newBook(){
        return "/newNovel.html";
    }

    @PostMapping("/form_novel")
    public Novel formBook(@RequestParam(name = "title") String title, @RequestParam(name = "author") Author author,
                          @RequestParam(name = "rating") Double rating, @RequestParam(name = "price") Double price){
        Novel novel = new Novel();
        novel.setTitle(title);
        novel.setAuthor(author);
        novel.setRating(rating);
        novel.setPrice(price);
        return novelService.save(novel);
    }




}
