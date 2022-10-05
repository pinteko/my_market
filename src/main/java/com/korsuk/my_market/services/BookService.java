package com.korsuk.my_market.services;

import com.korsuk.my_market.products.Book;
import com.korsuk.my_market.products.Student;
import com.korsuk.my_market.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book getBookById (int id) {
        return bookRepository.findById(id);
    }

    public Book getBookByTitle (String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getAllBooks(){
        return bookRepository.getBooks();
    }

    public void addBook(String title, String author, double rating, double price) {
        bookRepository.addBook(title, author, rating, price);
    }

    public void deleteBook(int id) {
        bookRepository.deleteBook(id);
    }

    public void updateBook(int id, String title, String author, double rating, double price) {
        bookRepository.updateBook(id, title, author, rating, price);
    }

}
