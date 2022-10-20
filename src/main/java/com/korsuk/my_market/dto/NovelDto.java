package com.korsuk.my_market.dto;

import com.korsuk.my_market.products.Novel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NovelDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private Double rating;

    private Double price;

    public NovelDto(Novel novel) {
        this.id = novel.getId();
        this.title = novel.getTitle();
        this.author = new AuthorDto(novel.getAuthor());
        this.rating = novel.getRating();
        this.price = novel.getPrice();
    }
}