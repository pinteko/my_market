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

    public Book getBookById (Integer id) {
        return bookRepository.findById(id);
    }

    public Book getBookByTitle (String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getAllBooks(){
        return bookRepository.getBooks();
    }

    public Book addBook(String title, String author, Double rating, Double price) {
      return bookRepository.addBook(title, author, rating, price);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteBook(id);
    }

    public void updateBook(Integer id, String title, String author, Double rating, Double price) {
        bookRepository.updateBook(id, title, author, rating, price);
    }

    public void changeScore(Integer bookId, Double delta) {
        Book book = bookRepository.findById(bookId);
        book.setRating(book.getRating() + delta);
    }
}
