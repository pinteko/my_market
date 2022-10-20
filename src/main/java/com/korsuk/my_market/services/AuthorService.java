package com.korsuk.my_market.services;

import com.korsuk.my_market.products.Author;
import com.korsuk.my_market.repo.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author save(Author author) {
        Author returnAuthor = authorRepository.findAuthorByNameAndSurname(author.getName(), author.getSurname());
        return Objects.requireNonNullElseGet(returnAuthor, () -> authorRepository.save(author));
    }

    public Author update (Author author) {
        return authorRepository.save(author);
    }
}
