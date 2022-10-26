package com.korsuk.my_market.dto;

import com.korsuk.my_market.products.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDto {

    private Long id;

    private String name;

    private String surname;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
    }
}
