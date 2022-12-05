package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.dto.NovelToSave;
import com.korsuk.my_market.exceptions.ResourceNotFoundException;
import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.dto.CartNotEntity;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.services.AuthorService;
import com.korsuk.my_market.services.CartService;
import com.korsuk.my_market.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/novels")
public class NovelController {

    private final NovelService novelService;
    private final AuthorService authorService;

    private final CartService cartService;


    @GetMapping()
    public Page<NovelDto> getNovels(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                    @RequestParam(name = "min_rating", required = false) Double minRating,
                                    @RequestParam(name = "max_rating", required = false) Double maxRating,
                                    @RequestParam(name = "min_price", required = false) Double minPrice,
                                    @RequestParam(name = "max_price", required = false) Double maxPrice,
                                    @RequestParam(name = "title_part", required = false ) String titlePart,
                                    @RequestParam(name = "names", required = false ) String name,
                                    @RequestParam(name = "surname", required = false ) String surname) {
//        return novelService.getAllNovels();
        if (page < 1) {page = 1;}

        return novelService.findNovels(page, minRating, maxRating, minPrice, maxPrice, titlePart, name, surname);

    }

    @GetMapping("/{id}")
    public NovelDto show(@PathVariable("id") Long id) {
        NovelDto novelDto = novelService.getNovelByIdDto(id);
        if (novelDto == null) {
            throw  new ResourceNotFoundException("Novel not found with id: " + id);
        }
        else {
            return novelDto;
        }
    }


    @GetMapping("/edit/change_rating")
    public void changeRating(@RequestParam Long novel_id, @RequestParam Double delta){
        novelService.changeRating(novel_id, delta);
    }

    @DeleteMapping("/edit/delete_novel")
    public void deleteBook(@RequestParam Long novel_id){
        novelService.deleteBookById(novel_id);
    }


    @GetMapping("/edit/new_novel")
    public String newBook(){
        return "/newNovel.html";
    }

    @PostMapping()
    public NovelDto addNovel(@RequestBody NovelToSave newNovel){
        Author author = new Author();
        author.setName(newNovel.getAuthorName());
        author.setSurname(newNovel.getAuthorSurname());
       Author authorSaved = authorService.save(author);
       Novel novel = new Novel();
       novel.setTitle(newNovel.getTitle());
       novel.setAuthor(authorSaved);
       novel.setRating(newNovel.getRating());
       novel.setPrice(newNovel.getPrice());
       return novelService.save(novel);
    }




}
