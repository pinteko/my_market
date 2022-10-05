package com.korsuk.my_market.repo;

import com.korsuk.my_market.products.Book;
import com.korsuk.my_market.products.Cart;
import com.korsuk.my_market.products.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {
    private List<Book> books;
    private int id;

    @PostConstruct
    public void init() {
        books = new ArrayList<>(Arrays.asList(
                new Book(1, "Invisible Monsters", "Chuck Palahniuk", 4.8, 12.3),
                new Book(2, "Under the Dome", "Stephen King", 4.6, 9.7),
                new Book(3, "Three comrades", "Remark", 4.8, 11.0),
                new Book(4, "The Thanatonauts", "Bernard Werber", 4.9, 13.2),
                new Book(5, "The Three Musketeers", "Alexandre Dumas", 4.7, 12.4)
        ));
        id = 5;
    }

    public Book findById(int id) {
        return books.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(new Book());
    }

    public Book findByTitle(String title) {
        return books.stream().filter(p -> Objects.equals(p.getTitle(), title)).findFirst().orElse(new Book());
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(String title, String author, double rating, double price) {
        books.add(new Book(++id, title, author, rating, price));
    }

    public void deleteBook(int id) {
        if (id > 0 && id <= books.size()) {
            books.remove(books.get(id)); }
    }

    public void updateBook(int id, String title, String author, double rating, double price) {
        if (id > 0 && id <= books.size()) {
            books.get(id).setTitle(title);
            books.get(id).setAuthor(author);
            books.get(id).setRating(rating);
            books.get(id).setPrice(price);
        }
    }

}
