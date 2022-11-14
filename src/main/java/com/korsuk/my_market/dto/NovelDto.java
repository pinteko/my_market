package com.korsuk.my_market.dto;

import com.korsuk.my_market.products.Novel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
public class NovelDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private Double rating;

    private Double price;

    private Integer count;

    public NovelDto(Novel novel) {
        this.id = novel.getId();
        this.title = novel.getTitle();
        this.author = new AuthorDto(novel.getAuthor());
        this.rating = novel.getRating();
        this.price = novel.getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        NovelDto novelDto = (NovelDto) obj;
        return Objects.equals(id, novelDto.id);
    }
    @Override
    public int hashCode() {
        final int someHash = 49;
        return (id == null ? 0 : id.hashCode()) + someHash;
    }
}