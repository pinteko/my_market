package com.korsuk.my_market.converters;

import com.korsuk.my_market.dto.AuthorDto;
import com.korsuk.my_market.products.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public Author dtoToEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName(), authorDto.getSurname());
    }

    public AuthorDto entityToDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getSurname());
    }
}
