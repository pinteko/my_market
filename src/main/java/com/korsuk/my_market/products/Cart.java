package com.korsuk.my_market.products;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Getter
public class Cart {
    List<Book> books;

    public Cart() {}

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);}
    }

    public void deleteBook(Book book) {
        if (book != null) {
            books.remove(book);}
    }
}
